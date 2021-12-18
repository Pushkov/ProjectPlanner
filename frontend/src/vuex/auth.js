import {AUTH_ERROR, AUTH_LOGIN, AUTH_LOGOUT, AUTH_REQUEST, AUTH_SUCCESS} from "./actions/auth";

const auth = {
    state: {
        // token: localStorage.getItem("user-token") || "",
        token: localStorage.getItem("user-token") || "",
        position: localStorage.getItem('user-position') || '',
        username: localStorage.getItem('user-name') || '',
        status: ""
    },
    getters: {
        getUsername: state => state.username,
        getPosition: state => state.position,
        isAuthenticated: state => !!state.token,
        authStatus: state => state.status,
        getUserToken: state => state.token
    },
    actions: {

        [AUTH_LOGIN]: ({commit}, user) => {
            commit(AUTH_REQUEST);
            localStorage.setItem('user-token', user.token);
            localStorage.setItem('user-name', user.login);
            localStorage.setItem('user-position', user.position);
            commit(AUTH_LOGIN, user);
            commit(AUTH_SUCCESS);
        },
        [AUTH_ERROR]: ({commit}, err) => {
            commit(AUTH_ERROR, err);
            localStorage.removeItem('user-token');
            localStorage.removeItem('user-position');
            localStorage.removeItem('user-name');
        },

        [AUTH_LOGOUT]: ({commit}) => {
            // localStorage.removeItem('user-token');
            // localStorage.removeItem('user-position');
            // localStorage.removeItem('user-name');

            return new Promise(() => {
                commit(AUTH_LOGOUT);
                localStorage.removeItem('user-token');
                localStorage.removeItem('user-position');
                localStorage.removeItem('user-name');
                // resolve();
            });
        }

    },
    mutations: {
        [AUTH_LOGIN]: (state, user) => {
            state.token = user.token;
            state.position = user.position;
            state.username = user.login;
        },

        [AUTH_REQUEST]: state => {
            state.status = "loading";
        },
        [AUTH_SUCCESS]: state => {
            state.status = "success";
        },
        [AUTH_ERROR]: state => {
            state.status = "error";
            state.token = "";
        },
        [AUTH_LOGOUT]: state => {
            state.status = "logout";
            state.token = '';
            state.position = '';
            state.username = '';
        }
    }
};

export default auth;