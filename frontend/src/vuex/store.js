import Vue from 'vue'
import Vuex from 'vuex'
import appStore from "@/vuex/appStore";
import usersStore from "@/vuex/usersStore";
import rolesStore from "@/vuex/rolesStore";
import searchStore from "@/vuex/searchStore";

Vue.use(Vuex);

let store = new Vuex.Store({
    modules: {
        appStore,
        usersStore,
        rolesStore,
        searchStore
    },
    state: {},
    getters: {},
    mutations: {},
    actions: {}

});

export default store;