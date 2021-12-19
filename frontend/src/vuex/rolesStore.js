import {AXIOS} from "@/vuex/axios-export"
import {AUTH_ERROR} from "@/vuex/actions/auth";

const rolesStore = {
    state: {
        isBusy: false,
        roles: [],
        role: {},
    },
    getters: {
        ROLES: state => state.roles,
        ROLE: state => state.role,
        user_token: (state, getters) => getters.getUserToken
    },
    actions: {
        SET_TABLE_BUSY: ({commit}, isStateTable) => {
            commit('SET_TABLE_BUSY', isStateTable);
        },

        GET_ALL_ROLES: async ({commit, dispatch, getters}) => {
            await AXIOS.get('/roles',
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    console.log(' role responce')
                    commit('SET_ROLES', responce.data);
                    commit('SET_ERROR', {});
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        GET_ROLE: async ({commit, dispatch, getters}, id) => {
            await AXIOS.get('/roles/' + id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(responce => {
                    commit('SET_ROLE', responce.data);
                    commit('SET_ERROR', false);
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },

        CREATE_ROLE: ({dispatch, getters}, role) => {
            AXIOS.post('/roles', role,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(() => {
                    dispatch('GET_ALL_ROLES');
                    dispatch('ACTION_CLOSE_MODAL');
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        UPDATE_ROLE: ({dispatch, getters}, role) => {
            AXIOS.put('/roles/' + role.id, role,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(() => {
                    dispatch('GET_ALL_ROLES');
                    dispatch('ACTION_CLOSE_MODAL');
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        },
        DELETE_ROLE: ({dispatch, getters}, role) => {
            AXIOS.delete('/roles/' + role.id,
                {
                    headers:
                        {'Authorization': getters.user_token}
                })
                .then(() => {
                    dispatch('GET_ALL_ROLES');
                    dispatch('SET_MODAL_STATE', false);
                    dispatch('SET_ERROR', {})
                })
                .catch(() => {
                    dispatch(AUTH_ERROR);
                    window.location.reload();
                })
        }
    },

    mutations: {
        SET_TABLE_BUSY: (state, isStateTable) => {
            state.isBusy = isStateTable;
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles; // Или
        },
        SET_ROLE: (state, role) => {
            state.role = role;
        },
    }
};

export default rolesStore;