<template>
    <div slot="footer" class="row text-center">
                <span class="col-2" v-if="!isEdit && !isDelete">
                    <button
                            class="basic-btn-cansel text-black btn btn-outline-danger rounded-lg"
                            @click="attemptDeleteItem"
                    >
                    Delete
                </button>
                </span>
        <span class="col-auto" v-if="isDelete">
            <h5> Подтвердите удаление</h5>
        </span>
        <span class="col-auto" v-if="isDelete">
                    <button
                            class="basic-btn-cansel text-black btn btn-outline-danger rounded-lg"
                            @click="deleteItem"
                    >
                    Confirm delete
                        </button>
        </span>


        <span class="col-2" v-if=" !isEdit  && !isDelete">
                    <button
                            class="basic-btn-cansel text-black btn btn-outline-warning rounded-lg"
                            @click="editItem"
                    >
                    Edit
                </button>
                </span>
        <span class="col-2" v-if="isEdit">
                    <button
                            class="basic-btn-cansel text-black btn btn-outline-success rounded-lg"
                            @click="saveItem"
                    >
                    Save
                </button>
                </span>
        <span class="col-sm"></span>
        <span class="col-2" v-if="!isEdit && !isDelete">
                <button
                        class="basic-btn-cansel text-black btn btn-outline-secondary rounded-lg"
                        @click="modalCLose"
                >
                    Close
                </button>
            </span>
        <span class="col-2" v-if=" (isEdit || isDelete)">
                <button
                        class="basic-btn-cansel text-black btn btn-outline-secondary rounded-lg"
                        @click="cancel"
                >
                    Cancel
                </button>
            </span>
    </div>
</template>

<script>
    export default {
        name: "BasicModalFooter",
        data() {
            return {
                isDelete: false
            }
        },
        props: {
            isEdit: {
                type: Boolean,
                default: false
            },
            isCreate: {
                type: Boolean,
                default: false
            },

        },
        methods: {
            modalCLose() {
                this.$emit('modalClose');
                this.isCreate = false;
            },
            editItem() {
                this.$emit('editItem', true);
            },
            saveItem() {
                this.$emit('saveItem');
            },
            attemptDeleteItem() {
                this.isDelete = true;
            },
            deleteItem() {
                this.$emit('deleteItem');
            },
            cancel() {
                if (this.isCreate) {
                    this.$emit('modalClose');
                } else {
                    this.isDelete = false;
                    this.$emit('editItem', false);
                }
            }
        }
    }
</script>

<style scoped>

</style>