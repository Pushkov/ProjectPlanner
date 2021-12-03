<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="secondary">
            <b-navbar-brand>
                <strong>
                    <em>
                        <h4 class="text-white">Project planner</h4>
                    </em>
                </strong>
            </b-navbar-brand>

            <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

            <b-collapse id="nav-collapse" is-nav>
                <b-navbar-nav>
                    <b-nav-item href="#">
                        <router-link to="/planner/index">
                            <span class="text-white-50">
                                <search-icon/>
                            </span>
                        </router-link>
                    </b-nav-item>
                    <b-nav-item href="#">
                        <router-link to="/planner/overview">
                            <span class="text-white-50">{{$t("message.navigation.overview")}}</span>
                        </router-link>
                    </b-nav-item>
                    <b-nav-item href="#">
                        <router-link to="/planner/workshops">
                            <span class="text-white-50">{{$t("message.navigation.workshops")}}</span>
                        </router-link>
                    </b-nav-item>
                    <b-nav-item href="#">
                        <router-link to="/planner/title">
                            <span class="text-white-50">{{$t("message.navigation.title_list")}}</span>
                        </router-link>
                    </b-nav-item>

                    <b-nav-item href="#">
                        <router-link to="/planner/plans">
                            <span class="text-white-50">{{$t("message.navigation.plans")}}</span>
                        </router-link>
                    </b-nav-item>

                    <b-nav-item href="#">
                        <router-link to="/planner/projects">
                            <span class="text-white-50">{{$t("message.navigation.projects")}}</span>
                        </router-link>
                    </b-nav-item>

                    <b-nav-item href="#">
                        <router-link to="/planner/documents">
                            <span class="text-white-50">{{$t("message.navigation.documents")}}</span>
                        </router-link>
                    </b-nav-item>

                    <b-nav-item href="#">
                        <router-link to="/planner/personal">
                            <span class="text-white-50">{{$t("message.navigation.personal")}}</span>
                        </router-link>
                    </b-nav-item>

                    <b-nav-item href="#">
                        <router-link to="/planner/roles">
                            <span class="text-white-50">{{$t("message.navigation.roles")}}</span>
                        </router-link>
                    </b-nav-item>
                    <b-nav-item href="#">
                        <router-link to="/planner/permissions">
                            <span class="text-white-50">{{$t("message.navigation.permissions")}}</span>
                        </router-link>
                    </b-nav-item>
                </b-navbar-nav>

                <div class="row ml-auto mr-0">
                    <language-icon

                            class="ml-auto mr-1 text-white-50"
                    />
                    <b-form-select
                            v-model="currentLocale"
                            class="ml-auto mr-5 w-auto text-white rounded-lg bg-secondary"
                            value-field="value"
                            text-field="value"
                            disabled-field="notEnabled"
                            @change="setLocale"
                    >
                        <b-form-select-option :value="'ru'">RU</b-form-select-option>
                        <b-form-select-option :value="'en'">EN</b-form-select-option>
                    </b-form-select>
                    <b-dropdown text="Меню" right variant="outline-light" class=" col-sm ml-auto">
                        <b-dropdown-item>
                            <router-link to="/planner/index">
                                Стартовая
                            </router-link>
                        </b-dropdown-item>
                        <b-dropdown-divider></b-dropdown-divider>
                        <b-dropdown-item>
                            <router-link to="/planner/admin">
                                Admin
                            </router-link>
                        </b-dropdown-item>
                        <b-dropdown-divider></b-dropdown-divider>
                        <b-dropdown-item>
                            <router-link to="/planner/about">
                                About
                            </router-link>
                        </b-dropdown-item>
                        <b-dropdown-item>
                            <router-link to="/planner/logout">
                                LogOut
                            </router-link>
                        </b-dropdown-item>
                    </b-dropdown>
                </div>
            </b-collapse>

        </b-navbar>
    </div>
</template>

<script>

    import {mapActions, mapGetters} from "vuex";

    export default {
        name: "NavigationBar",

        data() {
            return {
                currentLocale: ''
            }
        },

        components: {},
        computed: {
            ...mapGetters([
                'GET_LOCALE',
            ]),
        },

        methods: {
            ...mapActions([
                'SET_APPLICATION_LOCALE',

            ]),
            setLocale() {
                this.SET_APPLICATION_LOCALE(this.currentLocale)
            },
            setUp() {
                this.currentLocale = this.GET_LOCALE;
                this.SET_APPLICATION_LOCALE(this.currentLocale)
            }
        },
        mounted() {
            this.setUp();
        }
    }
</script>

<style scoped>

</style>