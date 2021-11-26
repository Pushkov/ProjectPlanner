<template>
    <div class="basic-popup-body container-fluid">
        <template>
            <div>
                <b-form-select
                        v-model="selected"
                        :options="item"
                        class="mb-3"
                        value-field="id"
                        text-field="name"
                        disabled-field="notEnabled"
                ></b-form-select>

                <div class="mt-3">Selected: <strong>{{selected}}</strong></div>
            </div>
        </template>
    </div>
</template>

<script>
    export default {
        name: "RolePermissionModalView",
        data() {
            return {
                dataItem: {...this.item},
                selected: {}
            }
        },
        props: {
            item: {
                type: Array,
                default: () => {
                    return []
                }
            }
        },
        computed: {
            getSelected(id) {
                return this.item[id]
            }
        },
        methods: {
            returnItem() {
                this.$emit('returnPermission', this.item.filter(x => x.id === this.selected)[0]);
            }
        }

    }
</script>

<style scoped>

</style>