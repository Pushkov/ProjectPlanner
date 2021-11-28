import {AXIOS} from "@/vuex/axios-export"

const rolesStore = {
    state: {
        isBusy: false,
        roles: [],
        role: {},
    },
    getters: {
        ROLES: state => state.roles,
        ROLE: state => state.role,
    },
    actions: {
        SET_TABLE_BUSY: ({commit}, isStateTable) => {
            commit('SET_TABLE_BUSY', isStateTable);
        },

        GET_ALL_ROLES: async ({commit}) => {
            await AXIOS.get('/roles')
                .then(responce => {
                    commit('SET_ROLES', responce.data);
                    commit('SET_ERROR', {});
                })
        },
        GET_ROLE: async ({commit}, id) => {
            await AXIOS.get('/roles/' + id)
                .then(responce => {
                    commit('SET_ROLE', responce.data);
                    commit('SET_ERROR', false);
                })
        },

        CREATE_ROLE: ({dispatch}, role) => {
            AXIOS.post('/roles', role)
                .then(() => {
                    dispatch('GET_ALL_ROLES');
                    dispatch('ACTION_CLOSE_MODAL');
                }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        UPDATE_ROLE: ({dispatch}, role) => {
            AXIOS.put('/roles/' + role.id, role)
                .then(() => {
                    dispatch('GET_ALL_ROLES');
                    dispatch('ACTION_CLOSE_MODAL');
                }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        DELETE_ROLE: ({dispatch}, role) => {
            AXIOS.delete('/roles/' + role.id)
                .then(() => {
                    dispatch('GET_ALL_ROLES');
                    dispatch('SET_MODAL_STATE', false);
                    dispatch('SET_ERROR', {})
                })
        }
    },

    mutations: {
        SET_TABLE_BUSY: (state, isStateTable) => {
            state.isBusy = isStateTable;
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles; // Или
        },
        SET_ROLE: (state, role) => {
            state.role = role;
        },
    }
};

export default rolesStore;