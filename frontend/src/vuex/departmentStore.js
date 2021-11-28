import {AXIOS} from "@/vuex/axios-export";

const departmentStore = {
    state: {
        department: {},
        departments: []
    },
    getters: {
        DEPARTMENT: state => state.department,
        DEPARTMENTS: state => state.departments,
    },
    actions: {
        GET_ALL_DEPARTMENTS: async ({commit}) => {
            await AXIOS.get('/departments')
                .then(responce => {
                    commit('SET_DEPARTMENTS', responce.data);
                })
                .catch()
        },
        GET_DEPARTMENT: async ({commit}, id) => {
            await AXIOS.get('/roles/' + id)
                .then(responce => {
                    commit('SET_DEPARTMENT', responce.data);
                })
                .catch()
        },
    },
    mutations: {

        SET_DEPARTMENTS: (state, deps) => {
            state.departments = deps;
        },
        SET_DEPARTMENT: (state, dep) => {
            state.department = dep;
        },
    }
};

export default departmentStore;