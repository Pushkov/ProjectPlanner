import {AXIOS} from "@/vuex/axios-export"

const projectStore = {
    state: {
        projects: [],
        project: {},
    },
    getters: {
        PROJECTS: state => state.projects,
        PROJECT: state => state.project,
    },
    actions: {
        GET_ALL_PROJECTS: async ({commit}) => {
            await AXIOS.get('/projects')
                .then(responce => {
                    commit('SET_PROJECTS', responce.data);
                })
                .catch()
        },
        GET_PROJECT: async ({commit}, id) => {
            await AXIOS.get('/projects/' + id)
                .then(responce => {
                    commit('SET_PROJECT', responce.data);
                })
                .catch()
        },

        CREATE_PROJECT: ({dispatch}, project) => {
            AXIOS.post(
                '/projects',
                project
            ).then(() => {
                dispatch('GET_ALL_PROJECTS');
            }).catch()
        },
        UPDATE_PROJECT: ({dispatch}, project) => {
            AXIOS.put(
                '/projects/' + project.id,
                project
            ).then(() => {
                dispatch('GET_ALL_PROJECTS');
            }).catch()
        },
        DELETE_PROJECT: ({dispatch}, project) => {
            AXIOS.delete('/projects/' + project.id
            ).then(() => {
                dispatch('GET_ALL_PROJECTS');
            })
        }
    },
    mutations: {
        SET_PROJECTS: (state, value) => {
            state.projects = value;
        },
        SET_PROJECT: (state, value) => {
            state.project = value;
        },
    }
};

export default projectStore;