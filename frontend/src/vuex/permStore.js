import {AXIOS} from "@/vuex/axios-export"

const permStore = {
    state: {
        permissions: []
    },
    getters: {
        PERMISSIONS: state => state.permissions
    },
    actions: {
        GET_ALL_PERMISSIONS: async ({commit}) => {
            await AXIOS.get('/permissions')
                .then(responce => {
                    commit('SET_PERMISSIONS', responce.data);
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
    }
};

export default permStore;