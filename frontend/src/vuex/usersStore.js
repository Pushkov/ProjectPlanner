// import Vue from 'vue'
import {AXIOS} from "@/vuex/axios-export"

///tms/planner/api/v1/
const usersStore = {
    state: {
        isBusy: false,
        engineers: [],
        engStatuses: [],
    },
    getters: {
        ENGINEERS: state => state.engineers,
        ENGINEER_STATUSES: state => state.engStatuses,
        IS_BUSY: state => state.isBusy,
    },
    actions: {
        SET_TABLE_BUSY: ({commit}, isStateTable) => {
            commit('SET_TABLE_BUSY', isStateTable);
        },
        GET_ALL_ENGINEER_STATUSES: ({commit}) => {
            AXIOS.get('/engineer-statuses')
                .then(responce => {
                    commit('SET_STATUSES', responce.data);
                })
                .catch(
                )
        },
        GET_ALL_ENGINEERS: async ({commit}) => {
            await AXIOS.get('/engineers')
                .then(responce => {
                    commit('SET_ENGINEERS', responce.data);
                })
                .catch(
                )
        },
        CREATE_ENGINEER: ({dispatch}, engineer) => {
            AXIOS.post(
                '/engineers',
                engineer
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
                dispatch('ACTION_CLOSE_MODAL');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        UPDATE_ENGINEER: ({dispatch}, engineer) => {
            AXIOS.put(
                '/engineers/' + engineer.id,
                engineer
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
                dispatch('ACTION_CLOSE_MODAL');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        DELETE_ENGINEER: ({dispatch}, user) => {
            AXIOS.delete('/engineers/' + user.id
            ).then(() => {
                dispatch('GET_ALL_ENGINEERS');
            })
        }
    },
    mutations: {
        SET_STATUSES: (state, result) => {
            state.engStatuses = result;
        },
        SET_TABLE_BUSY: (state, isStateTable) => {
            state.isBusy = isStateTable;
        },
        SET_ENGINEERS: (state, engineers) => {
            state.engineers = engineers; // Или
        },
    }
};

export default usersStore;