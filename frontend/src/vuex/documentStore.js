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
        GET_ALL_DOCUMENTS_LIST: ({commit, dispatch}, init) => {
            if (init.isPageable !== undefined && init.isPageable) {
                AXIOS.get('/documents/count')
                    .then(response => {
                        let toPage = 0;
                        let sizeInPage = response.data;
                        let pages = 1;
                        if (init.inPage !== undefined && init.currentPage !== undefined) {
                            toPage = init.currentPage;
                            sizeInPage = init.inPage;
                            pages = Math.ceil(response.data / init.inPage);
                        }

                        if (response.data > init.inPage) {
                            console.log('set pages ' + pages);
                            commit('SET_PAGES', pages);
                            dispatch('GET_DOCUMENTS_PAGE',
                                {
                                    "page": toPage,
                                    "offset": sizeInPage,
                                })
                        } else {
                            commit('SET_PAGES', 1);
                            dispatch('GET_ALL_DOCUMENTS');
                        }
                    })
                    .catch(() => {
                        commit('SET_COUNT', 0);
                    });
            } else {
                commit('SET_PAGES', 1);
                dispatch('GET_ALL_DOCUMENTS');
            }
        },
        GET_ALL_DOCUMENTS: async ({commit}) => {
            await AXIOS.get('/documents')
                .then(response => {
                    commit('SET_DOCUMENTS', response.data)
                })
        },
        GET_DOCUMENTS_PAGE: async ({commit}, param) => {
            await AXIOS.get('/documents/page',
                {
                    params: param
                })
                .then(response => {
                    commit('SET_PAGE', param.page)
                    commit('SET_DOCUMENTS', response.data.content);
                })
        },
        GET_DOCUMENT: async ({commit}, id) => {
            await AXIOS.get('/documents/' + id)
                .then(response => {
                    commit('SET_DOCUMENT', response.data);
                })
                .catch(error => {
                    commit('SET_DOCUMENT', {});
                    console.log('document error ' + error)
                })
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
        DELETE_DOCUMENT: ({dispatch}, id) => {
            AXIOS.delete('/documents/' + id
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