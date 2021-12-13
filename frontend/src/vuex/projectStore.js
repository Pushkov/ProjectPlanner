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
        GET_PROJECT: async ({commit}, project) => {
            await AXIOS.get('/projects/' + project.id)
                .then(responce => {
                    commit('SET_PROJECT', responce.data);
                })
                .catch()
        },

        CREATE_PERMISSION: ({dispatch}, permission) => {
            AXIOS.post(
                '/permissions',
                permission
            ).then(() => {
                dispatch('GET_ALL_PERMISSIONS');
                dispatch('ACTION_CLOSE_MODAL');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        UPDATE_PERMISSION: ({dispatch}, permission) => {
            AXIOS.put(
                '/permissions/' + permission.id,
                permission
            ).then(() => {
                dispatch('GET_ALL_PERMISSIONS');
                dispatch('ACTION_CLOSE_MODAL');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        DELETE_PERMISSION: ({dispatch}, permission) => {
            AXIOS.delete('/permissions/' + permission.id
            ).then(() => {
                dispatch('GET_ALL_PERMISSIONS');
                dispatch('SET_MODAL_STATE', false);
                dispatch('SET_ERROR', {});
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