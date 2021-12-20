import {AXIOS} from "@/vuex/axios-export"
import {AUTH_ERROR} from "@/vuex/actions/auth";

const documentStore = {
    state: {
        documents: [],
        document: {},
        format: {},
        formats: [],
        dummy: {},

    },
    getters: {
        DOCUMENTS: state => state.documents,
        DOCUMENT: state => state.document,
        FORMATS: state => state.formats,
        FORMAT: state => state.format,
    },
    actions: {
        GET_ALL_DOCUMENTS_LIST: ({commit, dispatch, getters}, init) => {
            if (init.isPageable !== undefined && init.isPageable) {
                AXIOS.get('/documents/count',
                    {
                        headers:
                            {'Authorization': getters.getUserToken}
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
                        dispatch(AUTH_ERROR);
                        // window.location.reload();
                    })
            } else {
                commit('SET_PAGES', 1);
                dispatch('GET_ALL_DOCUMENTS');
            }
        },
        GET_ALL_DOCUMENTS: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/documents',
                {
                    headers:
                        {'Authorization': getters.getUserToken}
                })
                .then(response => {
                    commit('SET_DOCUMENTS', response.data)
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        GET_DOCUMENTS_PAGE: async ({commit, dispatch, getters}, param) => {
            await AXIOS.get('/documents/page',
                {
                    params: param,
                    headers:
                        {'Authorization': getters.getUserToken}
                })
                .then(response => {
                    commit('SET_PAGE', param.page)
                    commit('SET_DOCUMENTS', response.data.content);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        GET_DOCUMENT: async ({commit, getters}, id) => {
            await AXIOS.get('/documents/' + id,
                {
                    headers:
                        {'Authorization': getters.getUserToken}
                })
                .then(response => {
                    commit('SET_DOCUMENT', response.data);
                })
                .catch(() => {
                    commit('SET_DOCUMENT', {});
                })
        },

        CREATE_DOCUMENT: ({dispatch, getters}, document) => {
            AXIOS.post(
                '/documents',
                document,
                {
                    headers:
                        {'Authorization': getters.getUserToken}
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
                        {'Authorization': getters.getUserToken}
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
                        {'Authorization': getters.getUserToken}
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
                        {'Authorization': getters.getUserToken}
                }
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            }).catch()
        },
        DELETE_DOCUMENT: ({dispatch, getters}, id) => {
            AXIOS.delete('/documents/' + id,
                {
                    headers:
                        {'Authorization': getters.getUserToken}
                }
            ).then(() => {
                dispatch('GET_ALL_DOCUMENTS');
            })
        },
        GET_ALL_FORMATS: ({commit, getters}) => {
            AXIOS.get('/formats',
                {
                    headers:
                        {'Authorization': getters.getUserToken}
                })
                .then(response =>
                    commit('SET_FORMATS', response.data)
                )

        },
        ADD_PROJECT_IN_DOCUMENT: async ({dispatch, getters}, dao) => {
            await AXIOS.put('/documents/' + dao.document + '/projects/' + dao.project + '/add', {},
                {
                    headers:
                        {'Authorization': getters.getUserToken}
                })
                .then(() => {
                    dispatch('GET_DOCUMENT', dao.document)
                })
        },
        REMOVE_PROJECT_IN_DOCUMENT: async ({commit, getters}, dao) => {
            await AXIOS.put('/documents/' + dao.document + '/projects/' + dao.project + '/remove', {},
                {
                    headers:
                        {'Authorization': getters.getUserToken}
                })
                .then(() => {
                    commit('SET_DUMMY', {})
                })
        },
        SIGN_DESIGNER: async ({getters}, doc) => {
            await AXIOS.put('/documents/' + doc.id + '/designer/' + getters.getUserName,
                {},
                {
                    headers: {'Authorization': getters.getUserToken}
                })

        },
        SIGN_VERIFIER: async ({getters}, doc) => {
            await AXIOS.put('/documents/' + doc.id + '/verifier/' + getters.getUserName,
                {},
                {
                    headers: {'Authorization': getters.getUserToken}
                })

        },
        SIGN_NORM_CONTROL: async ({getters}, doc) => {
            await AXIOS.put('/documents/' + doc.id + '/normcontrol/' + getters.getUserName,
                {},
                {
                    headers: {'Authorization': getters.getUserToken}
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