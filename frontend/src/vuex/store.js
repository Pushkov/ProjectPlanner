import Vue from 'vue'
import Vuex from 'vuex'
import appStore from "@/vuex/appStore";
import usersStore from "@/vuex/usersStore";
import rolesStore from "@/vuex/rolesStore";
import searchStore from "@/vuex/searchStore";
import titleListStore from "@/vuex/titleListStore";
import permStore from "@/vuex/permStore";

Vue.use(Vuex);

let store = new Vuex.Store({
    modules: {
        appStore,
        usersStore,
        rolesStore,
        searchStore,
        titleListStore,
        permStore
    },
    state: {
        isModalShow: false,
        isModalEdit: false,
        isError: false,
        error_message: 'ниудачко'
    },
    getters: {
        IS_MODAL_SHOW: state => state.isModalShow,
        IS_MODAL_EDIT: state => state.isModalEdit,
        ERROR_MESSAGE: state => state.error_message,
        IS_ERROR: state => state.isError,
    },
    actions: {
        SET_IS_MODAL_EDIT: ({commit}, value) => {
            commit('SET_MODAL_EDIT_STATE', value);
        },
        CLOSE_MODAL: ({commit}) => {
            commit('SET_MODAL_STATE', false);
        },
    },
    mutations: {
        SET_MODAL_STATE: (state, value) => {
            state.isModalShow = value;
        },
        SET_MODAL_EDIT_STATE: (state, value) => {
            state.isModalEdit = value;
        },
        SET_ERROR_MESSAGE: (state, msg) => {
            state.error_message = msg;
        },
        SET_IS_ERROR: (state, value) => {
            state.isError = value;
        },
    }

});

export default store;