import {AXIOS} from "@/vuex/axios-export"
import {AUTH_ERROR} from "@/vuex/actions/auth";

const planStore = {
    state: {
        plans: [],
        plan: {},
        planPoint: {},
        errorPP: {}
    },
    getters: {
        PLANS: state => state.plans,
        PLAN: state => state.plan,
        PLAN_POINT: state => state.planPoint,
        PLAN_POINT_ERROR: state => state.errorPP,
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        GET_ALL_PLANS: async ({commit, dispatch, getters}, param) => {
            await AXIOS.get('/plans',
                {
                    params: param,
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_PLANS', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        GET_PLAN: async ({commit, dispatch, getters}, plan) => {
            await AXIOS.get('/plans/' + plan.year + '/' + plan.month + '/' + plan.department_id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_PLAN', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },

        GET_PLAN_POINT: async ({commit, dispatch, getters}, planPoint) => {
            await AXIOS.get('/planpoints/' + planPoint,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_PLAN_POINT', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        UPDATE_PLAN_POINT: async ({commit, getters}, item) => {
            await AXIOS.put(
                '/planpoints/' + item.id,
                item,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                commit('SET_PLAN_POINT_ERROR', {})
            })
                .catch()
        },
        SAVE_PLAN_POINT: async ({commit, getters}, item) => {
            await AXIOS.post('/planpoints', item,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(() => {
                    commit('SET_PLAN_POINT_ERROR', {})
                })
                .catch()
        },
        DELETE_PLAN_POINT: ({commit, getters}, id) => {
            AXIOS.delete('/planpoints/' + id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                commit('SET_PLAN_POINT_ERROR', {})
            })
                .catch()
        },

    },
    mutations: {
        SET_PLANS: (state, value) => {
            state.plans = value;
        },
        SET_PLAN: (state, value) => {
            state.plan = value;
        },
        SET_PLAN_POINT: (state, value) => {
            state.planPoint = value;
        },
        SET_PLAN_POINT_ERROR: (state, value) => {
            state.errorPP = value;
        },
    }
};

export default planStore;