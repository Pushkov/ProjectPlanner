// import Vue from 'vue'
import {AXIOS} from "@/vuex/axios-export"

///tms/planner/api/v1/
const usersStore = {
    state: {
        isBusy: false,
        engineers: [],
        error_list_loading_message: 'ниудачко'
    },
    getters: {
        ENGINEERS: state => state.engineers,
        IS_BUSY: state => state.isBusy,
        ERROR_LIST_LOAIDNG_MESSAGE: state => state.error_list_loading_message,
    },
    actions: {
        SET_TABLE_BUSY: ({commit}, isStateTable) => {
            commit('SET_TABLE_BUSY', isStateTable);
        },

        GET_ALL_ENGINEERS: async ({commit}) => {
            await AXIOS.get('/tms/planner/api/v1/engineers')
                .then(responce => {
                    commit('SET_ENGINEERS', responce.data);
                })
                .catch(error => {
                        commit('SET_ERROR_LIST_LOADING_MESSAGE', error.message)
                    }
                )
        },
        CREATE_ENGINEER: ({dispatch}, engineer) => {
            AXIOS.post(
                '/admin/engineers',
                engineer
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
            })
        },
        UPDATE_ENGINEER: ({dispatch}, engineer) => {
            AXIOS.put(
                '/admin/engineers/' + engineer.id,
                engineer
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
            })
        },
        DELETE_ENGINEER: ({dispatch}, user) => {
            AXIOS.delete('/admin/engineers/' + user.id
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
            })
        }
    },
    mutations: {
        SET_TABLE_BUSY: (state, isStateTable) => {
            state.isBusy = isStateTable;
        },
        SET_ENGINEERS: (state, engineers) => {
            // Vue.set(state, 'engineers', [...engineers]) // Или
            state.engineers = engineers; // Или
            state.isBusy = false;
            state.error_list_loading_message = '';
        },
        SET_ERROR_LIST_LOADING_MESSAGE: (state, msg) => {
            state.error_list_loading_message = msg;
        }
    }
};

export default usersStore;