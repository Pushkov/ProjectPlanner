import {AXIOS} from "@/vuex/axios-export"

const titleListStore = {
    state: {
        tasks: [],
        titleListsYears: [],
        titleLists: [],
        titleList: {},
        error: {},
    },
    getters: {
        TASKS: state => state.tasks,
        TITLE_LIST_ERROR: state => state.error,
        TITLE_LIST: state => state.titleList,
        TITLE_LISTS: state => state.titleLists,
        TITLE_LISTS_YEARS: state => state.titleListsYears,
    },
    actions: {
        GET_TITLE_LIST: async ({commit}, year) => {
            if (year === undefined || year < 0) {
                await AXIOS.get('/titlelists')
                    .then(responce => {
                        commit('SET_TITLE_LISTS', responce.data);
                    })
                    .catch()
            } else {
                await AXIOS.get('/titlelists/' + year)
                    .then(responce => {
                        commit('SET_TITLE_LIST', responce.data);
                        commit('SET_TITLE_LISTS', []);
                    })
                    .catch()
            }
        },
        GET_TITLE_YEARS: async ({commit}) => {
            await AXIOS.get('/titlelists/years')
                .then(responce => {
                    commit('SET_TITLE_LISTS_YEARS', responce.data);
                })
                .catch()
        },
        SAVE_MEMO: async ({commit, dispatch}, memo, year) => {
            if (memo.id === undefined) {
                await AXIOS.post('/memos', memo)
                    .then(() => {
                        dispatch('GET_TITLE_LIST', year);
                        commit('SET_TITLE_LIST_ERROR', {});
                    })
                    .catch(error => {
                        if (error.response) {
                            commit('SET_TITLE_LIST_ERROR', error.response.data);
                        }
                    })
            } else {

                await AXIOS.put(
                    '/memos/' + memo.id, memo)
                    .then(() => {
                        dispatch('GET_TITLE_LIST', year);
                        commit('SET_TITLE_LIST_ERROR', {});
                    })
                    .catch(error => {
                        if (error.response) {
                            commit('SET_TITLE_LIST_ERROR', error.response.data);
                        }
                    })
            }
        },
        DELETE_MEMO: ({dispatch}, memo, year) => {
            AXIOS.delete('/memos/' + memo.id)
                .then(() => {
                    dispatch('GET_TITLE_LIST', year);
                })
        },
        SAVE_TASK: async ({commit, dispatch}, task, year) => {
            if (task.id === undefined) {
                await AXIOS.post('/tasks', task)
                    .then(() => {
                        dispatch('GET_TITLE_LIST', year);
                        commit('SET_TITLE_LIST_ERROR', {});
                    })
                    .catch(error => {
                        if (error.response) {
                            commit('SET_TITLE_LIST_ERROR', error.response.data);
                        }
                    })
            } else {

                await AXIOS.put(
                    '/tasks/' + task.id, task)
                    .then(() => {
                        dispatch('GET_TITLE_LIST', year);
                        commit('SET_TITLE_LIST_ERROR', {});
                    })
                    .catch(error => {
                        if (error.response) {
                            commit('SET_TITLE_LIST_ERROR', error.response.data);
                        }
                    })
            }
        },
        DELETE_TASK: ({dispatch}, task, year) => {
            AXIOS.delete('/tasks/' + task.id)
                .then(() => {
                    dispatch('GET_TITLE_LIST', year);
                })
        },
        GET_ALL_TASKS: async ({commit}) => {
            await AXIOS.get('/tasks')
                .then(responce => {
                    commit('SET_TASKS', responce.data);
                })
                .catch()
        },


    },
    mutations: {
        SET_TASKS: (state, result) => {
            state.tasks = result;
        },
        SET_TITLE_LISTS: (state, result) => {
            state.titleLists = result;
        },
        SET_TITLE_LIST: (state, result) => {
            state.titleList = result;
        },
        SET_TITLE_LISTS_YEARS: (state, result) => {
            state.titleListsYears = result;
        },
        SET_TITLE_LIST_ERROR: (state, result) => {
            state.error = result;
        },

    }
};

export default titleListStore;