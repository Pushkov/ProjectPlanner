// import Vue from 'vue'
import {AXIOS} from "@/vuex/axios-export"

///tms/planner/api/v1/
const rolesStore = {
    state: {
        isBusy: false,
        roles: [],
        error_list_loading_message: 'ниудачко'
    },
    getters: {
        ROLES: state => state.roles,
        IS_BUSY: state => state.isBusy,
        ERROR_LIST_LOAIDNG_MESSAGE: state => state.error_list_loading_message,
    },
    actions: {
        SET_TABLE_BUSY: ({commit}, isStateTable) => {
            commit('SET_TABLE_BUSY', isStateTable);
        },

        GET_ALL_ROLES: async ({commit}) => {
            await AXIOS.get('/roles')
                .then(responce => {
                    commit('SET_ROLES', responce.data);
                })
                .catch(error => {
                        commit('SET_ERROR_LIST_LOADING_MESSAGE', error.message)
                    }
                )
        },
        CREATE_ROLE: ({dispatch}, engineer) => {
            AXIOS.post(
                '/admin/engineers',
                engineer
            ).then(() => {
                dispatch('GET_ALL_ROLES');
            })
        },
        UPDATE_ROLE: ({dispatch}, engineer) => {
            AXIOS.put(
                '/admin/engineers/' + engineer.id,
                engineer
            ).then(() => {
                dispatch('GET_ALL_ROLES');
            })
        },
        DELETE_ROLE: ({dispatch}, user) => {
            AXIOS.delete('/admin/engineers/' + user.id
            ).then(() => {
                dispatch('GET_ALL_ROLES');
            })
        }
    },
    mutations: {
        SET_TABLE_BUSY: (state, isStateTable) => {
            state.isBusy = isStateTable;
        },
        SET_ROLES: (state, roles) => {
            // Vue.set(state, 'engineers', [...engineers]) // Или
            state.roles = roles; // Или
            state.isBusy = false;
            state.error_list_loading_message = '';
        },
        SET_ERROR_LIST_LOADING_MESSAGE: (state, msg) => {
            state.error_list_loading_message = msg;
        }
    }
};

export default rolesStore;