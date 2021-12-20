import {AXIOS} from "@/vuex/axios-export"
import {AUTH_ERROR} from "@/vuex/actions/auth";

const permStore = {
    state: {
        permissions: [],
    },
    getters: {
        PERMISSIONS: state => state.permissions,
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        GET_ALL_PERMISSIONS: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/permissions',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_PERMISSIONS', responce.data);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        CREATE_PERMISSION: ({dispatch, getters}, permission) => {
            AXIOS.post(
                '/permissions',
                permission,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(() => {
                    dispatch('GET_ALL_PERMISSIONS');
                    dispatch('ACTION_CLOSE_MODAL');
                })
                .catch()
        },
        UPDATE_PERMISSION: ({dispatch, getters}, permission) => {
            AXIOS.put(
                '/permissions/' + permission.id,
                permission,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(() => {
                    dispatch('GET_ALL_PERMISSIONS');
                    dispatch('ACTION_CLOSE_MODAL');
                })
                .catch()
        },
        DELETE_PERMISSION: ({dispatch, getters}, permission) => {
            AXIOS.delete('/permissions/' + permission.id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                }
            ).then(() => {
                dispatch('GET_ALL_PERMISSIONS');
                dispatch('SET_MODAL_STATE', false);
                dispatch('SET_ERROR', {});
            })
                .catch()
        }
    },
    mutations: {
        SET_PERMISSIONS: (state, permissions) => {
            state.permissions = permissions;
        },
    }
};

export default permStore;