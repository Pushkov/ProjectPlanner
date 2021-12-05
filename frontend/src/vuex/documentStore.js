import {AXIOS} from "@/vuex/axios-export"

const documentStore = {
    state: {
        documents: [],
        document: {},
    },
    getters: {
        DOCUMENTS: state => state.documents,
        DOCUMENT: state => state.document,
    },
    actions: {
        GET_ALL_DOCUMENTS: async ({commit}) => {
            await AXIOS.get('/documents')
                .then(responce => {
                    commit('SET_DOCUMENTS', responce.data);
                })
                .catch()
        },
        GET_DOCUMENT: async ({commit}, id) => {
            await AXIOS.get('/documents/' + id)
                .then(responce => {
                    commit('SET_DOCUMENT', responce.data);
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
        DELETE_DOCUMENT: async ({dispatch}, id) => {
            await AXIOS.delete('/documents/' + id
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            })
        }
    },
    mutations: {
        SET_DOCUMENTS: (state, value) => {
            state.documents = value;
        },
        SET_DOCUMENT: (state, value) => {
            state.document = value;
        },
    }
};

export default documentStore;