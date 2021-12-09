<template>
    <tr @click="selectTask">
        <td>
            {{task.number}}
        </td>
        <td>
            {{task.name}}
        </td>
    </tr>
</template>

<script>

    export default {
        name: "TaskTitleRow",
        props: {
            task: {
                type: Object,
                default: () => {
                    return {}
                }
            },
        },
        components: {},
        methods: {
            selectTask() {
                this.$emit('selectTask', this.task);
            }

        },
    }
</script>

<style scoped>

</style>