import {AXIOS} from "@/vuex/axios-export"

const workshopStore = {
    state: {
        workshops: [],
        workshop: {},
    },
    getters: {
        WORKSHOPS: state => state.workshops,
        WORKSHOP: state => state.workshop,
    },
    actions: {
        GET_ALL_WORKSHOPS: async ({commit}) => {
            await AXIOS.get('/workshops')
                .then(responce => {
                    commit('SET_WORKSHOPS', responce.data);
                })
                .catch()
        },
        GET_WORKSHOP: async ({commit}, id) => {
            await AXIOS.get('/workshops/' + id)
                .then(responce => {
                    commit('SET_WORKSHOP', responce.data);
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
        SET_WORKSHOPS: (state, value) => {
            state.workshops = value;
        },
        SET_WORKSHOP: (state, value) => {
            state.workshop = value;
        },
    }
};

export default workshopStore;