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
        isModalShowSecond: false,
        isModalEdit: false,
        isModalDelete: false,
        isError: false,
        error_message: 'ниудачко'
    },
    getters: {
        IS_MODAL_SHOW: state => state.isModalShow,
        IS_MODAL_SECOND_SHOW: state => state.isModalShowSecond,
        IS_MODAL_EDIT: state => state.isModalEdit,
        IS_MODAL_DELETE: state => state.isModalDelete,
        ERROR_MESSAGE: state => state.error_message,
        IS_ERROR: state => state.isError,
    },
    actions: {
        SET_IS_MODAL_EDIT: ({commit}, value) => {
            commit('SET_MODAL_EDIT_STATE', value);
        },
        SET_IS_MODAL_DELETE: ({commit}, value) => {
            commit('SET_MODAL_EDIT_STATE', value);
        },
        SET_MODAL_STATE: ({commit}, value) => {
            commit('SET_MODAL_STATE', value);
        },
        SET_MODAL_SECOND_STATE: ({commit}, value) => {
            commit('SET_MODAL_SECOND_STATE', value);
        },
    },
    mutations: {
        SET_MODAL_STATE: (state, value) => {
            state.isModalShow = value;
        },
        SET_MODAL_SECOND_STATE: (state, value) => {
            state.isModalShowSecond = value;
        },
        SET_MODAL_EDIT_STATE: (state, value) => {
            state.isModalEdit = value;
        },
        SET_MODAL_DELETE_STATE: (state, value) => {
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