// import Vue from 'vue'
import {AXIOS} from "@/vuex/axios-export"

///tms/planner/api/v1/
const usersStore = {
    state: {
        isBusy: false,
        engineers: [],
        engStatuses: [],
        engCount: 0,
        foundEngineers: []
    },
    getters: {
        ENGINEERS: state => state.engineers,
        FOUND_ENGINEERS: state => state.foundEngineers,
        ENGINEER_STATUSES: state => state.engStatuses,
        ENGINEER_COUNT: state => state.engCount,
        IS_BUSY: state => state.isBusy,
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        SET_TABLE_BUSY: ({commit}, isStateTable) => {
            commit('SET_TABLE_BUSY', isStateTable);
        },

        GET_ALL_ENGINEER_LIST: ({commit, dispatch, getters}, init) => {
            if (init.isPageable !== undefined && init.isPageable) {

                AXIOS.get('/engineers/count',
                    {
                        headers:
                            {'Authorization': getters.user_token}
                    })
                    .then(responce => {
                        let toPage = 0;
                        let sizeInPage = responce.data;
                        let pages = 1;
                        if (init !== undefined && init.inPage !== undefined && init.currentPage !== undefined) {
                            toPage = init.currentPage;
                            sizeInPage = init.inPage;
                            pages = Math.ceil(responce.data / init.inPage);
                        }
                        if (responce.data > init.inPage) {
                            commit('SET_PAGES', pages);
                            dispatch('GET_ALL_ENGINEER_PAGE',
                                {
                                    "page": toPage,
                                    "offset": sizeInPage,
                                })
                        } else {
                            commit('SET_PAGES', 1);
                            dispatch('GET_ALL_ENGINEERS')
                        }
                    })
                    .catch(() => {
                        commit('SET_COUNT', 0);
                    });
            } else {
                commit('SET_PAGES', 1);
                dispatch('GET_ALL_ENGINEERS');
            }

        },

        GET_ALL_ENGINEER_PAGE: ({commit, getters}, param) => {
            AXIOS.get('/engineers/page',
                {
                    params: param,
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_PAGE', param.page)
                    commit('SET_ENGINEERS', responce.data.content);
                })
                .catch(() => {
                })
        },

        GET_ALL_ENGINEER_STATUSES: ({commit, getters}) => {
            AXIOS.get('/engineer-statuses',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_STATUSES', responce.data);
                })
                .catch(
                )
        },
        GET_ALL_ENGINEERS: async ({commit, getters}) => {
            await AXIOS.get('/engineers',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_ENGINEERS', responce.data);
                })
                .catch(
                )
        },
        GET_ALL_ENGINEERS_LAST_NAME_START: async ({commit, getters}, param) => {
            await AXIOS.get('/engineers/search/last-name',
                {
                    params: param,
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_FOUND_ENGINEERS', responce.data);
                })
                .catch(error => {
                        commit('SET_ERROR_LIST_LOADING_MESSAGE', error.message)
                    }
                )
        },
        CREATE_ENGINEER: ({dispatch, getters}, engineer) => {
            AXIOS.post(
                '/engineers',
                engineer,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
                dispatch('ACTION_CLOSE_MODAL');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        UPDATE_ENGINEER: ({dispatch, getters}, engineer) => {
            AXIOS.put(
                '/engineers/' + engineer.id,
                engineer,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
                dispatch('ACTION_CLOSE_MODAL');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        DELETE_ENGINEER: ({dispatch, getters}, user) => {
            AXIOS.delete('/engineers/' + user.id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
                dispatch('SET_MODAL_STATE', false);
            })
        }
    },
    mutations: {
        SET_COUNT: (state, result) => {
            state.engCount = result;
        },
        SET_STATUSES: (state, result) => {
            state.engStatuses = result;
        },
        SET_TABLE_BUSY: (state, isStateTable) => {
            state.isBusy = isStateTable;
        },
        SET_ENGINEERS: (state, engineers) => {
            state.engineers = engineers;
        },
        SET_FOUND_ENGINEERS: (state, engineers) => {
            state.foundEngineers = engineers;
        },
    }
};

export default usersStore;