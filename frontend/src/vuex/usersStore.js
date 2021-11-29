// import Vue from 'vue'
import {AXIOS} from "@/vuex/axios-export"

///tms/planner/api/v1/
const usersStore = {
    state: {
        isBusy: false,
        engineers: [],
        engStatuses: [],
        engCount: 0,
    },
    getters: {
        ENGINEERS: state => state.engineers,
        ENGINEER_STATUSES: state => state.engStatuses,
        ENGINEER_COUNT: state => state.engCount,
        IS_BUSY: state => state.isBusy,
    },
    actions: {
        SET_TABLE_BUSY: ({commit}, isStateTable) => {
            commit('SET_TABLE_BUSY', isStateTable);
        },

        GET_ALL_ENGINEER_LIST: ({commit, dispatch}, init) => {
            if (init.isPageable !== undefined && init.isPageable) {

                AXIOS.get('/engineers/count')
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

        GET_ALL_ENGINEER_PAGE: ({commit}, param) => {
            AXIOS.get('/engineers/page',
                {
                    params: param

                })
                .then(responce => {
                    commit('SET_PAGE', param.page)
                    commit('SET_ENGINEERS', responce.data.content);
                })
                .catch(() => {
                })
        },

        GET_ALL_ENGINEER_STATUSES: ({commit}) => {
            AXIOS.get('/engineer-statuses')
                .then(responce => {
                    commit('SET_STATUSES', responce.data);
                })
                .catch(
                )
        },
        GET_ALL_ENGINEERS: async ({commit}) => {
            await AXIOS.get('/engineers')
                .then(responce => {
                    commit('SET_ENGINEERS', responce.data);
                })
                .catch(
                )
        },
        CREATE_ENGINEER: ({dispatch}, engineer) => {
            AXIOS.post(
                '/engineers',
                engineer
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
                dispatch('ACTION_CLOSE_MODAL');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        UPDATE_ENGINEER: ({dispatch}, engineer) => {
            AXIOS.put(
                '/engineers/' + engineer.id,
                engineer
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
                dispatch('ACTION_CLOSE_MODAL');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        DELETE_ENGINEER: ({dispatch}, user) => {
            AXIOS.delete('/engineers/' + user.id
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
            state.engineers = engineers; // Или
        },
    }
};

export default usersStore;