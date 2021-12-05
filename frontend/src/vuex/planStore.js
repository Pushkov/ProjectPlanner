import {AXIOS} from "@/vuex/axios-export"

const planStore = {
    state: {
        plans: [],
        plan: {},
    },
    getters: {
        PLANS: state => state.plans,
        PLAN: state => state.plan,
    },
    actions: {
        GET_ALL_PLANS: async ({commit}) => {
            await AXIOS.get('/plans')
                .then(responce => {
                    commit('SET_PLANS', responce.data);
                })
                .catch()
        },
        GET_PLAN: async ({commit}, plan) => {
            await AXIOS.get('/PLANS/' + plan.id)
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
        }
    },
    mutations: {
        SET_PLANS: (state, value) => {
            state.plans = value;
        },
        SET_PLAn: (state, value) => {
            state.plan = value;
        },
    }
};

export default planStore;