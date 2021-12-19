import {AXIOS} from "@/vuex/axios-export"

const projectStore = {
    state: {
        projects: [],
        project: {},
    },
    getters: {
        PROJECTS: state => state.projects,
        PROJECT: state => state.project,
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        GET_ALL_PROJECTS: async ({commit, getters}) => {
            await AXIOS.get('/projects',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_PROJECTS', responce.data);
                })
                .catch(() => {
                    commit('SET_PROJECTS', []);
                })
        },
        GET_PROJECT: async ({commit, getters}, id) => {
            await AXIOS.get('/projects/' + id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_PROJECT', responce.data);
                })
                .catch()
        },

        CREATE_PROJECT: ({dispatch, getters}, project) => {
            AXIOS.post(
                '/projects',
                project,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_PROJECTS');
            }).catch()
        },
        UPDATE_PROJECT: ({dispatch, getters}, project) => {
            AXIOS.put(
                '/projects/' + project.id,
                project,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_PROJECTS');
            }).catch()
        },
        DELETE_PROJECT: ({dispatch, getters}, project) => {
            AXIOS.delete('/projects/' + project.id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
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