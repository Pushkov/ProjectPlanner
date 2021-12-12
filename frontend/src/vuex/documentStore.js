import {AXIOS} from "@/vuex/axios-export"

const documentStore = {
    state: {
        documents: [],
        document: {},
        format: {},
        formats: [],
    },
    getters: {
        DOCUMENTS: state => state.documents,
        DOCUMENT: state => state.document,
        FORMATS: state => state.formats,
        FORMAT: state => state.format,
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
                .catch(() => {
                    commit('SET_DOCUMENTS', []);
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
                .catch(() => {
                    commit('SET_DOCUMENTS', []);
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

        CREATE_DOCUMENT: ({dispatch}, document) => {
            AXIOS.post(
                '/documents',
                document
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        CREATE_SIGNED_DOCUMENT: ({dispatch}, document) => {
            AXIOS.post(
                '/documents/signed',
                document
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        UPDATE_DOCUMENT: ({dispatch}, document) => {
            AXIOS.put(
                '/documents/' + document.id,
                document
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        UPDATE_SIGNED_DOCUMENT: ({dispatch}, document) => {
            AXIOS.put(
                '/documents/' + document.id + '/signed',
                document
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
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
        },
        GET_ALL_FORMATS: ({commit}) => {
            AXIOS.get('/formats')
                .then(response =>
                    commit('SET_FORMATS', response.data)
                )

        }
    },
    mutations: {
        SET_DOCUMENTS: (state, value) => {
            state.documents = value;
        },
        SET_DOCUMENT: (state, value) => {
            state.document = value;
        },
        SET_FORMATS: (state, value) => {
            state.formats = value;
        },
        SET_FORMAT: (state, value) => {
            state.format = value;
        },
    }
};

export default documentStore;