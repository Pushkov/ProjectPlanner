<template>
    <div class="h-screen bg-yellow-200">
        <NavigationBarBlank/>
        <div class="container" style="margin-top: 10%">
            <div class="jumbotron">

                <form class="form-inline justify-content-center" @submit.prevent="login">
                    <label class="mr-lg-2">Пользователь:</label>
                    <input type="text" v-model="username" placeholder="ЛОГИН" class="form-control mr-lg-2">
                    <label class="mr-lg-2">Пароль:</label>
                    <input type="password" v-model="password" placeholder="ПАРОЛЬ" class="form-control mr-lg2-2">
                    <button type="submit" class="btn btn-secondary ml-lg-2">
                        <router-link to="/">
                            Вход
                        </router-link>
                    </button>
                </form>

            </div>
        </div>
    </div>
</template>
<script>
    import NavigationBarBlank from "../components/nav/NavigationBarBlank";

    export default {
        components: {NavigationBarBlank}

    }
</script>