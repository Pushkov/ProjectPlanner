<template>
    <tr v-if="Object.keys(role).length > 0" @click="itemInModal">
        <td v-for="key of Object.keys(role)"
            :role="key"
            :key="key"
        >
            <ul v-if="Array.isArray(role[key])">
                <li v-for="value of role[key]"
                    :role="value"
                    :key="value.name"
                >
                    {{value.name}}
                </li>
            </ul>
            <template v-else>
                {{role[key]}}
            </template>
        </td>
    </tr>
    <tr v-else>
        <td>object is empty</td>
    </tr>
</template>

<script>
    export default {
        name: "RoleTableRow",
        props: {
            role: {
                type: Object,
                default: () => {
                    return {}
                }
            }
        },
        methods: {
            itemInModal() {
                this.$emit('showModal', this.role);
            }
        },
    }
</script>

<style scoped>

</style>