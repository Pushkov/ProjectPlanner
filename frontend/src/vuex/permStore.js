import {AXIOS} from "@/vuex/axios-export"

const permStore = {
    state: {
        permissions: [],
        permissionsExcludingRole: [],
    },
    getters: {
        PERMISSIONS: state => state.permissions,
        PERMISSIONS_EXCLUDE_ROLE: state => state.permissionsExcludingRole
    },
    actions: {
        GET_ALL_PERMISSIONS: async ({commit}) => {
            await AXIOS.get('/permissions')
                .then(responce => {
                    commit('SET_PERMISSIONS', responce.data);
                })
                .catch()
        },
        GET_ALL_PERMISSIONS_EXCLUDE_ROLE: async ({commit}, id) => {
            await AXIOS.get('/permissions/exlude-role/' + id)
                .then(responce => {
                    commit('SET_PERMISSIONS_EXCLUDE_ROLE', responce.data);
                })
                .catch()
        },


        CREATE_PERMISSION: ({dispatch}, permission) => {
            AXIOS.post(
                '/permissions',
                permission
            ).then(() => {
                dispatch('GET_ALL_PERMISSIONS');
            })
        },
        UPDATE_PERMISSION: ({dispatch}, permission) => {
            AXIOS.put(
                '/permissions/' + permission.id,
                permission
            ).then(() => {
                dispatch('GET_ALL_PERMISSIONS');
            })
        },
        DELETE_PERMISSION: ({dispatch}, permission) => {
            AXIOS.delete('/permissions/' + permission.id
            ).then(() => {
                dispatch('GET_ALL_PERMISSIONS');
            })
        }
    },
    mutations: {
        SET_PERMISSIONS: (state, permissions) => {
            state.permissions = permissions;
        },
        SET_PERMISSIONS_EXCLUDE_ROLE: (state, permissions) => {
            state.permissionsExcludingRole = permissions;
        },
    }
};

export default permStore;