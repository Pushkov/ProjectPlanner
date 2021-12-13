import {AXIOS} from "@/vuex/axios-export"

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
    },
    actions: {
        GET_ALL_PLANS: async ({commit}, param) => {
            await AXIOS.get('/plans',
                {
                    params: param
                })
                .then(responce => {
                    commit('SET_PLANS', responce.data);
                })
                .catch()
        },
        GET_PLAN: async ({commit}, plan) => {
            await AXIOS.get('/plans/' + plan.year + '/' + plan.month + '/' + plan.department_id)
                .then(responce => {
                    commit('SET_PLAN', responce.data);
                })
                .catch()
        },

        GET_PLAN_POINT: async ({commit}, planPoint) => {
            await AXIOS.get('/planpoints/' + planPoint)
                .then(responce => {
                    commit('SET_PLAN_POINT', responce.data);
                })
                .catch()
        },
        UPDATE_PLAN_POINT: async ({commit}, item) => {
            await AXIOS.put(
                '/planpoints/' + item.id,
                item
            ).then(() => {
                commit('SET_PLAN_POINT_ERROR', {})
            }).catch()
        },
        SAVE_PLAN_POINT: async ({commit}, item) => {
            await AXIOS.post('/planpoints', item)
                .then(() => {
                    commit('SET_PLAN_POINT_ERROR', {})
                }).catch();
        },
        DELETE_PLAN_POINT: ({commit}, id) => {
            AXIOS.delete('/planpoints/' + id
            ).then(() => {
                commit('SET_PLAN_POINT_ERROR', {})
            })
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