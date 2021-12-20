import {AXIOS} from "@/vuex/axios-export"
import {AUTH_ERROR} from "@/vuex/actions/auth";

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
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        GET_TITLE_LIST: async ({commit, dispatch, getters}, year) => {
            if (year === undefined || year < 0) {
                await AXIOS.get('/titlelists',
                    {
                        headers:
                            {'Authorization': getters.user_token}
                    })
                    .then(responce => {
                        commit('SET_TITLE_LISTS', responce.data);
                    })
                    .catch(() => {
                        dispatch(AUTH_ERROR);
                        window.location.reload();
                    })
            } else {
                await AXIOS.get('/titlelists/' + year,
                    {
                        headers:
                            {'Authorization': getters.user_token}
                    })
                    .then(responce => {
                        commit('SET_TITLE_LIST', responce.data);
                        commit('SET_TITLE_LISTS', []);
                    })
                    .catch(() => {
                        dispatch(AUTH_ERROR);
                        window.location.reload();
                    })
            }
        },
        GET_TITLE_YEARS: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/titlelists/years',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_TITLE_LISTS_YEARS', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        SAVE_MEMO: async ({commit, dispatch, getters}, memo, year) => {
            if (memo.id === undefined) {
                await AXIOS.post('/memos', memo,
                    {
                        headers:
                            {'Authorization': getters.user_token}
                    })
                    .then(() => {
                        dispatch('GET_TITLE_LIST', year);
                        commit('SET_TITLE_LIST_ERROR', {});
                    })
                    .catch()
            } else {

                await AXIOS.put(
                    '/memos/' + memo.id, memo,
                    {
                        headers:
                            {'Authorization': getters.user_token}
                    })
                    .then(() => {
                        dispatch('GET_TITLE_LIST', year);
                        commit('SET_TITLE_LIST_ERROR', {});
                    })
                    .catch(() => {
                        dispatch(AUTH_ERROR);
                        window.location.reload();
                    })
            }
        },
        DELETE_MEMO: ({dispatch, getters}, memo, year) => {
            AXIOS.delete('/memos/' + memo.id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(() => {
                    dispatch('GET_TITLE_LIST', year);
                })
                .catch()
        },
        SAVE_TASK: async ({commit, dispatch, getters}, task, year) => {
            if (task.id === undefined) {
                await AXIOS.post('/tasks', task,
                    {
                        headers:
                            {'Authorization': getters.user_token}
                    })
                    .then(() => {
                        dispatch('GET_TITLE_LIST', year);
                        commit('SET_TITLE_LIST_ERROR', {});
                    })
                    .catch()
            } else {

                await AXIOS.put(
                    '/tasks/' + task.id, task,
                    {
                        headers:
                            {'Authorization': getters.user_token}
                    })
                    .then(() => {
                        dispatch('GET_TITLE_LIST', year);
                        commit('SET_TITLE_LIST_ERROR', {});
                    })
                    .catch()
            }
        },
        DELETE_TASK: ({dispatch, getters}, task, year) => {
            AXIOS.delete('/tasks/' + task.id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(() => {
                    dispatch('GET_TITLE_LIST', year);
                })
                .catch()
        },
        GET_ALL_TASKS: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/tasks',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_TASKS', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
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