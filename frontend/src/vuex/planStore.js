import {AXIOS} from "@/vuex/axios-export"

const planStore = {
    state: {
        plans: [],
        plan: {},
        planPoint: {}
    },
    getters: {
        PLANS: state => state.plans,
        PLAN: state => state.plan,
        PLAN_POINT: state => state.planPoint,
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

        CREATE_PERMISSION: ({dispatch}, permission) => {
            AXIOS.post(
                '/permissions',
                permission
            ).then(() => {
                dispatch('GET_ALL_PERMISSIONS');
                dispatch('ACTION_CLOSE_MODAL');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        UPDATE_PERMISSION: ({dispatch}, permission) => {
            AXIOS.put(
                '/permissions/' + permission.id,
                permission
            ).then(() => {
                dispatch('GET_ALL_PERMISSIONS');
                dispatch('ACTION_CLOSE_MODAL');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        DELETE_PERMISSION: ({dispatch}, permission) => {
            AXIOS.delete('/permissions/' + permission.id
            ).then(() => {
                dispatch('GET_ALL_PERMISSIONS');
                dispatch('SET_MODAL_STATE', false);
                dispatch('SET_ERROR', {});
            })
        },
        GET_PLAN_POINT: async ({commit}, planPoint) => {
            await AXIOS.get('/planpoints/' + planPoint)
                .then(responce => {
                    commit('SET_PLAN_POINT', responce.data);
                })
                .catch()
        },
        SAVE_PLAN_POINT: async ({dispatch}, item, plan) => {
            if (item.id > 0) {
                await AXIOS.put(
                    '/planpoints/' + item.id,
                    item
                ).then(() => {
                    dispatch('GET_PLAN', plan)
                }).catch()
            } else {
                await AXIOS.post('/planpoints', item)
                    .then(() => {
                        dispatch('GET_PLAN', plan)
                    }).catch();
            }
        },
        DELETE_PLAN_POINT: ({dispatch}, id, plan) => {
            AXIOS.delete('/planpoints/' + id
            ).then(() => {
                dispatch('GET_PLAN', plan)
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
    }
};

export default planStore;