// import Vue from 'vue'
import {AXIOS} from "@/vuex/axios-export"

///tms/planner/api/v1/
const rolesStore = {
    state: {
        isBusy: false,

        roles: [],
        role: {},
        error_list_loading_message: 'ниудачко'
    },
    getters: {
        ROLES: state => state.roles,
        ROLE: state => state.role,
        IS_ROLES_BUSY: state => state.isBusy,
        ERROR_ROLE_LIST_LOAIDNG_MESSAGE: state => state.error_list_loading_message,
    },
    actions: {
        SET_TABLE_BUSY: ({commit}, isStateTable) => {
            commit('SET_TABLE_BUSY', isStateTable);
        },

        GET_ALL_ROLES: async ({commit}) => {
            await AXIOS.get('/roles')
                .then(responce => {
                    commit('SET_ROLES', responce.data);
                    commit('SET_IS_ERROR', false);
                    commit('SET_ERROR_MESSAGE', '');
                })
                .catch(error => {
                    commit('SET_IS_ERROR', true);
                    commit('SET_ERROR_MESSAGE', error.message);
                })
        },
        GET_ROLE: async ({commit}, id) => {
            await AXIOS.get('/roles/' + id)
                .then(responce => {
                    commit('SET_ROLE', responce.data);
                    commit('SET_IS_ERROR', false);
                    commit('SET_ERROR_MESSAGE', '');
                })
                .catch(error => {
                    commit('SET_IS_ERROR', true);
                    commit('SET_ERROR_MESSAGE', error.message);
                })
        },

        CREATE_ROLE: ({dispatch}, role) => {
            AXIOS.post('/roles', role)
                .then(() => {
                    dispatch('GET_ALL_ROLES');
                })
        },
        UPDATE_ROLE: ({dispatch}, role) => {
            AXIOS.put('/roles/' + role.id, role)
                .then(() => {
                    dispatch('GET_ALL_ROLES');
                })
        },
        DELETE_ROLE: ({dispatch}, role) => {
            AXIOS.delete('/roles/' + role.id)
                .then(() => {
                    dispatch('GET_ALL_ROLES');
                })
        }
    },
    mutations: {
        SET_TABLE_BUSY: (state, isStateTable) => {
            state.isBusy = isStateTable;
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles; // Или
            state.isBusy = false;
            state.error_list_loading_message = '';
        },
        SET_ROLE: (state, role) => {
            state.role = role;
        },
        SET_ERROR_LIST_LOADING_MESSAGE: (state, msg) => {
            state.error_list_loading_message = msg;
        }
    }
};

export default rolesStore;