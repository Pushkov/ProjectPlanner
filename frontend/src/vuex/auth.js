import {AUTH_ERROR, AUTH_LOGIN, AUTH_LOGOUT, AUTH_REQUEST, AUTH_SUCCESS} from "./actions/auth";

const auth = {
    state: {
        token: localStorage.getItem("user-token") || "",
        position: localStorage.getItem('user-position') || '',
        username: localStorage.getItem('user-name') || '',
        userId: localStorage.getItem('user-id') || '',
        status: ""
    },
    getters: {
        getUserName: state => state.username,
        getPosition: state => state.position,
        isAuthenticated: state => !!state.token,
        authStatus: state => state.status,
        getUserToken: state => state.token,
        getUserId: state => state.userId,
    },
    actions: {

        [AUTH_LOGIN]: ({commit, dispatch}, user) => {
            commit(AUTH_REQUEST);
            localStorage.setItem('user-token', 'Bearer_' + user.token);
            localStorage.setItem('user-name', user.login);
            localStorage.setItem('user-position', user.position);
            localStorage.setItem('user-locale', user.locale);
            localStorage.setItem('user-id', user.id);
            commit(AUTH_LOGIN, user);
            commit(AUTH_SUCCESS);
            dispatch('SET_APPLICATION_LOCALE',
                (user.locale === undefined || user.locale === null) ? 'en' : user.locale
            )
        },
        [AUTH_ERROR]: ({commit}, err) => {
            commit(AUTH_ERROR, err);
            localStorage.removeItem('user-token');
            localStorage.removeItem('user-position');
            localStorage.removeItem('user-name');
            localStorage.removeItem('user-locale');
            localStorage.removeItem('user-id');
        },

        [AUTH_LOGOUT]: ({commit}) => {
            return new Promise(() => {
                commit(AUTH_LOGOUT);
                localStorage.removeItem('user-token');
                localStorage.removeItem('user-position');
                localStorage.removeItem('user-name');
                localStorage.removeItem('user-locale');
                localStorage.removeItem('user-id');
            });
        }

    },
    mutations: {
        [AUTH_LOGIN]: (state, user) => {
            state.token = 'Bearer_' + user.token;
            // state.token = user.token;
            state.position = user.position;
            state.username = user.login;
            state.userLocale = user.locale;
            state.userId = user.id;
        },

        [AUTH_REQUEST]: state => {
            state.status = "loading";
        },
        [AUTH_SUCCESS]: state => {
            state.status = "success";
        },
        [AUTH_ERROR]: state => {
            state.status = "error";
            state.token = '';
            state.position = '';
            state.username = '';
            state.userId = '';
        },
        [AUTH_LOGOUT]: state => {
            state.status = "logout";
            state.token = '';
            state.position = '';
            state.username = '';
            state.userId = '';
        }
    }
};

export default auth;