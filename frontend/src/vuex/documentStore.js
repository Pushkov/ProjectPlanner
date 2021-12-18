import {AXIOS} from "@/vuex/axios-export"

const documentStore = {
    state: {
        documents: [],
        document: {},
        format: {},
        formats: [],
        dummy: {}
    },
    getters: {
        DOCUMENTS: state => state.documents,
        DOCUMENT: state => state.document,
        FORMATS: state => state.formats,
        FORMAT: state => state.format,
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        GET_ALL_DOCUMENTS_LIST: ({commit, dispatch, getters}, init) => {
            if (init.isPageable !== undefined && init.isPageable) {
                AXIOS.get('/documents/count',
                    {
                        headers:
                            {'Authorization': getters.user_token}
                    })
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
        GET_ALL_DOCUMENTS: async ({commit, getters}) => {
            await AXIOS.get('/documents',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(response => {
                    commit('SET_DOCUMENTS', response.data)
                })
                .catch(() => {
                    commit('SET_DOCUMENTS', []);
                })
        },
        GET_DOCUMENTS_PAGE: async ({commit, getters}, param) => {
            await AXIOS.get('/documents/page',
                {
                    params: param,
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(response => {
                    commit('SET_PAGE', param.page)
                    commit('SET_DOCUMENTS', response.data.content);
                })
                .catch(() => {
                    commit('SET_DOCUMENTS', []);
                })
        },
        GET_DOCUMENT: async ({commit, getters}, id) => {
            await AXIOS.get('/documents/' + id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(response => {
                    commit('SET_DOCUMENT', response.data);
                })
                .catch(error => {
                    commit('SET_DOCUMENT', {});
                    console.log('document error ' + error)
                })
        },

        CREATE_DOCUMENT: ({dispatch, getters}, document) => {
            AXIOS.post(
                '/documents',
                document,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        CREATE_SIGNED_DOCUMENT: ({dispatch, getters}, document) => {
            AXIOS.post(
                '/documents/signed',
                document,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            }).catch((error) => {
                if (error.response) {
                    dispatch('SET_ERROR', error.response.data);
                }
            })
        },
        UPDATE_DOCUMENT: ({dispatch, getters}, document) => {
            AXIOS.put(
                '/documents/' + document.id,
                document,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            }).catch()
        },
        UPDATE_SIGNED_DOCUMENT: ({dispatch, getters}, document) => {
            AXIOS.put(
                '/documents/' + document.id + '/signed',
                document,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            }).catch()
        },
        DELETE_DOCUMENT: ({dispatch, getters}, id) => {
            AXIOS.delete('/documents/' + id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            })
        },
        GET_ALL_FORMATS: ({commit, getters}) => {
            AXIOS.get('/formats',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(response =>
                    commit('SET_FORMATS', response.data)
                )

        },
        ADD_PROJECT_IN_DOCUMENT: async ({dispatch, getters}, dao) => {
            await AXIOS.put('/documents/' + dao.document + '/projects/' + dao.project + '/add',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(() => {
                    dispatch('GET_DOCUMENT', dao.document)
                })
        },
        REMOVE_PROJECT_IN_DOCUMENT: async ({commit, getters}, dao) => {
            await AXIOS.put('/documents/' + dao.document + '/projects/' + dao.project + '/remove',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(() => {
                    commit('SET_DUMMY', {})
                })
        },
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
        SET_DUMMY: (state, value) => {
            state.dummy = value;
        },
    }
};

export default documentStore;