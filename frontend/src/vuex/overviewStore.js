import {AXIOS} from "@/vuex/axios-export";
import {AUTH_ERROR} from "@/vuex/actions/auth";

const overviewStore = {
    state: {
        items: [],
        item: {},
        overviewYears: [],
        overviewMonths: [],
        overviewDepNames: [],
    },
    getters: {
        ITEMS: state => state.items,
        ITEM: state => state.item,
        OVERVIEW_MONTHS: state => state.overviewMonths,
        OVERVIEW_YEARS: state => state.overviewYears,
        OVERVIEW_DEP_NAMES: state => state.overviewDepNames,
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        GET_ITEMS: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/planprojects',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_ITEMS', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        GET_ITEM: async ({commit, dispatch, getters}, id) => {
            await AXIOS.get('/planprojects/' + id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_ITEM', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        GET_OVERVIEW_YEARS: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/planprojects/years',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_OVERVIEW_YEARS', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        GET_OVERVIEW_MONTHS: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/planprojects/months',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_OVERVIEW_MONTHS', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        GET_OVERVIEW_DEP_NAMES: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/planprojects/departments',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_OVERVIEW_DEP_NAMES', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        SEARCH_OVERVIEW_ITEM: async ({commit, dispatch, getters}, param) => {
            await AXIOS.get('/planprojects/search',
                {
                    params: param,
                    headers:
                        {'Authorization': getters.user_token}
                }).then(responce => {
                commit('SET_ITEMS', responce.data);
            })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
    },
    mutations: {
        SET_ITEMS: (state, value) => {
            state.items = value;
        },
        SET_ITEM: (state, value) => {
            state.item = value;
        },
        SET_OVERVIEW_YEARS: (state, value) => {
            state.overviewYears = value;
        },
        SET_OVERVIEW_MONTHS: (state, value) => {
            state.overviewMonths = value;
        },
        SET_OVERVIEW_DEP_NAMES: (state, value) => {
            state.overviewDepNames = value;
        },
    }
};

export default overviewStore;