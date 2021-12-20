import {AXIOS} from "@/vuex/axios-export";
import {AUTH_ERROR} from "@/vuex/actions/auth";

const departmentStore = {
    state: {
        isStructure: false,
        department: {},
        departments: [],


    },
    getters: {
        DEPARTMENT: state => state.department,
        DEPARTMENTS: state => state.departments,
        IS_STRUCTURE: state => state.isStructure,
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        GET_ALL_DEPARTMENTS: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/departments',
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            )
                .then(responce => {
                    commit('SET_DEPARTMENTS', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        GET_STRUCTURE_DEPARTMENTS: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/departments/structure',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_DEPARTMENTS', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        GET_DEPARTMENT: async ({commit, getters}, id) => {
            await AXIOS.get('/departments/' + id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_DEPARTMENT', responce.data);
                })
                .catch()
        },
        DELETE_DEPARTMENT: async ({dispatch, getters}, id) => {
            await AXIOS.delete('/departments/' + id),
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
                    .then(() => {
                        if (this.state.isStructure) {
                            dispatch('GET_STRUCTURE_DEPARTMENTS');
                        } else {
                            dispatch('GET_ALL_DEPARTMENTS');
                        }
                    })
                    .catch()
        },
        SET_IS_STRUCTURE: ({commit}, value) => {
            commit('SET_IS_STRUCTURE', value);
        }

    },
    mutations: {

        SET_DEPARTMENTS: (state, deps) => {
            state.departments = deps;
        },
        SET_DEPARTMENT: (state, dep) => {
            state.department = dep;
        },
        SET_IS_STRUCTURE: (state, value) => {
            state.isStructure = value;
        },
    }
};

export default departmentStore;