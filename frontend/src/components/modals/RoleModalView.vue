<template>
    <div class="basic-popup-body container-fluid">
        <template v-if="item !== undefined">
            <div class="row my-1 mx-2"
            >
                <div class="col-4 mr-2 ">Название</div>
                <input
                        class="col-sm text-secondary  border rounded-lg"
                        :readonly="!isEdit"
                        v-model="dataItem.name"
                />

                <div class="col-1 mr-2"/>
            </div>
            <div class="row my-1 mx-2 p-2"
            >
                <h5 class="m-auto col-sm">Разрешения для должности</h5>
                <plus-icon
                        v-if="isEdit"
                        class="col-1 mr-2"
                        fill-color="green"
                        @click="addPermission"
                />
                <div class="col-1 mr-2" v-else/>
            </div>
            <template v-if="dataItem.permissionsDto === undefined">
                undef
            </template>

            <template v-else-if="dataItem.permissionsDto.length > 0">
                <div
                        class="row my-1 mx-2 p-2"
                        v-for="perm of dataItem.permissionsDto"
                        :key="perm.id"

                >
                    <span class="m-auto col-sm">{{perm.name}}</span>
                    <minus-icon
                            class="col-1 mr-2"
                            fill-color="red"
                            v-if="isEdit"
                            @click="removePermission(perm)"
                    />
                    <div class="col-1 mr-2" v-else/>
                </div>
            </template>


            <template v-else>
                <span class="col-sm m-auto">Разрешения не заданы</span>
            </template>
        </template>
        <template v-else>
            <h2>{{ errorMessage }}</h2>
        </template>
    </div>
</template>

<script>

    export default {
        name: "RoleModalView",
        comments: {},
        data() {
            return {
                dataItem: {...this.item}
            }
        },
        props: {
            item: {
                type: Object,
                default: () => {
                    return {}
                }
            },
            isEdit: {
                type: Boolean,
                default: false
            },
            errorMessage: {
                type: String,
                default: ''
            }
        },
        computed: {},
        methods: {
            returnItem() {
                this.$emit('returnItem', this.dataItem);
            },
            addPermission() {
                this.$emit('addPermission');
            },
            removePermission(item) {
                this.$emit('removePermission', item);
            }
        }

    }
</script>

<style scoped>

</style>