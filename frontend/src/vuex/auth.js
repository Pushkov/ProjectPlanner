import {AUTH_ERROR, AUTH_LOGIN, AUTH_LOGOUT, AUTH_REQUEST, AUTH_SUCCESS} from "./actions/auth";

const auth = {
    state: {
        token: localStorage.getItem("user-token") || "",
        position: localStorage.getItem('user-position') || '',
        username: localStorage.getItem('user-name') || '',
        status: ""
    },
    getters: {
        getUserName: state => state.username,
        getPosition: state => state.position,
        isAuthenticated: state => !!state.token,
        authStatus: state => state.status,
        getUserToken: state => state.token
    },
    actions: {

        [AUTH_LOGIN]: ({commit, dispatch}, user) => {
            commit(AUTH_REQUEST);
            localStorage.setItem('user-token', user.token);
            localStorage.setItem('user-name', user.login);
            localStorage.setItem('user-position', user.position);
            localStorage.setItem('user-locale', user.locale);
            commit(AUTH_LOGIN, user);
            commit(AUTH_SUCCESS);
            console.log('locale ' + user.locale);
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
        },

        [AUTH_LOGOUT]: ({commit}) => {
            return new Promise(() => {
                commit(AUTH_LOGOUT);
                localStorage.removeItem('user-token');
                localStorage.removeItem('user-position');
                localStorage.removeItem('user-name');
                localStorage.removeItem('user-locale');
            });
        }

    },
    mutations: {
        [AUTH_LOGIN]: (state, user) => {
            // state.token = 'Bearer_' + user.token;
            state.token = user.token;
            state.position = user.position;
            state.username = user.login;
            state.userLocale = user.locale;
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