<template>
    <div>
        <b-navbar toggleable="lg" type="dark" variant="secondary">
            <b-collapse id="nav-collapse" is-nav>

                <b-navbar-nav class="ml-auto mr-auto">
                    <b-nav-text>status line</b-nav-text>
                </b-navbar-nav>

                <!--                <b-dropdown text="Enter login" right variant="outline-light" class="ml-auto">-->
                <!--                    <b-dropdown-item href="#">-->
                <!--                        <router-link to="/cabinet">-->
                <!--                            User data-->
                <!--                        </router-link>-->
                <!--                    </b-dropdown-item>-->
                <!--                    <b-dropdown-divider></b-dropdown-divider>-->
                <!--                    <b-dropdown-item>-->
                <!--                        <router-link to="/">-->
                <!--                            Index-->
                <!--                        </router-link>-->
                <!--                    </b-dropdown-item>-->
                <!--                    <b-dropdown-item>-->
                <!--                        <router-link to="/plans">-->
                <!--                            Plans-->
                <!--                        </router-link>-->
                <!--                    </b-dropdown-item>-->
                <!--                    <b-dropdown-item>-->
                <!--                        <router-link to="/projects">-->
                <!--                            Projects-->
                <!--                        </router-link>-->
                <!--                    </b-dropdown-item>-->
                <!--                    <b-dropdown-item>-->
                <!--                        <router-link to="/documents">-->
                <!--                            Documents-->
                <!--                        </router-link>-->
                <!--                    </b-dropdown-item>-->
                <!--                    <b-dropdown-item>-->
                <!--                        <router-link to="/users">-->
                <!--                            Users-->
                <!--                        </router-link>-->
                <!--                    </b-dropdown-item>-->
                <!--                    <b-dropdown-item>-->
                <!--                        <router-link to="/login">-->
                <!--                            LogOut-->
                <!--                        </router-link>-->
                <!--                    </b-dropdown-item>-->
                <!--                </b-dropdown>-->
            </b-collapse>
        </b-navbar>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        name: 'NavigationBarBlank',
        props: {},
        methods: {
            login() {
                const {username, password} = this;
                console.log('Username: ' + username + ' Password: ' + password);
                return axios.post('http://localhost:8080/api/login', {username, password})
                    .then(response => {
                        return response;
                    })
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
