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
        GET_DOCUMENT: async ({commit}, document) => {
            await AXIOS.get('/documents/' + document.id)
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
        SET_DOCUMENTS: (state, value) => {
            state.documents = value;
        },
        SET_DOCUMENT: (state, value) => {
            state.document = value;
        },
    }
};

export default documentStore;