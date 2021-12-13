import {AXIOS} from "@/vuex/axios-export";

const departmentStore = {
    state: {
        isStructure: false,
        department: {},
        departments: []
    },
    getters: {
        DEPARTMENT: state => state.department,
        DEPARTMENTS: state => state.departments,
        IS_STRUCTURE: state => state.isStructure,
    },
    actions: {
        GET_ALL_DEPARTMENTS: async ({commit}) => {
            await AXIOS.get('/departments')
                .then(responce => {
                    commit('SET_DEPARTMENTS', responce.data);
                })
                .catch(() => {
                    commit('SET_DEPARTMENTS', []);
                })
        },
        GET_STRUCTURE_DEPARTMENTS: async ({commit}) => {
            await AXIOS.get('/departments/structure')
                .then(responce => {
                    commit('SET_DEPARTMENTS', responce.data);
                })
                .catch(() => {
                    commit('SET_DEPARTMENTS', []);
                })
        },
        GET_DEPARTMENT: async ({commit}, id) => {
            await AXIOS.get('/departments/' + id)
                .then(responce => {
                    commit('SET_DEPARTMENT', responce.data);
                })
                .catch()
        },
        DELETE_DEPARTMENT: async ({dispatch}, id) => {
            await AXIOS.delete('/departments/' + id)
                .then(() => {
                    if (this.state.isStructure) {
                        dispatch('GET_STRUCTURE_DEPARTMENTS');
                    } else {
                        dispatch('GET_ALL_DEPARTMENTS');
                    }
                })
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