<template>
    <div v-if="currentDocument !== undefined " class="w-75 m-auto">

        <div class="my-2"><h4>{{ currentDocument.designation }}</h4></div>
        <table class="table ">
            <tbody>
            <tr>
                <td class="col-5">
                    {{ $t('document.designation') }}
                </td>
                <td colspan="2">
                    <b-input
                            class="col-sm text-secondary  border rounded-lg"
                            :readonly="!isEdit"
                            :value="currentDocument.designation "
                            v-model="currentDocument.designation "/>

                </td>
            </tr>
            <tr>
                <td>
                    {{ $t('document.name') }}
                </td>
                <td colspan="2">
                    <b-input
                            class="col-sm text-secondary  border rounded-lg"
                            :readonly="!isEdit"
                            :value="currentDocument.name "
                            v-model="currentDocument.name "/>
                </td>
            </tr>

            <tr v-if="currentDocument.documentApprovalsDto !== undefined &&  (currentDocument.documentApprovalsDto.designerName !== undefined  || isCreate)">
                <td>
                    {{ $t('document.designer') }}
                </td>
                <td>
                    <b-input
                            class="col-sm text-secondary  border rounded-lg"
                            :readonly="!isEdit"
                            :value="currentDocument.documentApprovalsDto.designerName"
                            v-model="currentDocument.documentApprovalsDto.designerName"/>
                </td>
                <td>
                    <b-input
                            class="col-sm text-secondary  border rounded-lg"
                            :readonly="!isEdit"
                            :value="currentDocument.documentApprovalsDto.designerSign"
                            v-model="currentDocument.documentApprovalsDto.designerSign"/>
                </td>
            </tr>
            <tr v-if="currentDocument.documentApprovalsDto !== undefined &&  currentDocument.documentApprovalsDto.verifierName !== undefined">
                <td>
                    {{ $t('document.verifier') }}
                </td>
                <td>
                    <b-input
                            class="col-sm text-secondary  border rounded-lg"
                            :readonly="!isEdit"
                            :value="currentDocument.documentApprovalsDto.verifierName"
                            v-model="currentDocument.documentApprovalsDto.verifierName"/>
                </td>
                <td>
                    <b-input
                            class="col-sm text-secondary  border rounded-lg"
                            :readonly="!isEdit"
                            :value="currentDocument.documentApprovalsDto.verifierSign "
                            v-model="currentDocument.documentApprovalsDto.verifierSign"/>
                </td>
            </tr>
            <tr v-if="currentDocument.documentApprovalsDto !== undefined &&  currentDocument.documentApprovalsDto.normControlName !== undefined">
                <td>
                    {{ $t('document.norm_contr') }}
                </td>
                <td>
                    <b-input
                            class="col-sm text-secondary  border rounded-lg"
                            :readonly="!isEdit"
                            :value="currentDocument.documentApprovalsDto.normControlName "
                            v-model="currentDocument.documentApprovalsDto.normControlName"/>
                </td>
                <td>
                    <b-input
                            class="col-sm text-secondary  border rounded-lg"
                            :readonly="!isEdit"
                            :value="currentDocument.documentApprovalsDto.normControlSign "
                            v-model="currentDocument.documentApprovalsDto.normControlSign"/>
                </td>
            </tr>
            <tr v-if="currentDocument.documentFormatDto !== undefined">
                <td :rowspan="getFormatsLength">
                    <div class="m-auto row text-center">
                        <div class="col-sm"/>
                        <div class="col-auto text-right">{{$t('document.sheets')}}</div>
                        <plus-icon
                                v-if="isEdit"
                                class="col-1"
                                fill-color="green"
                                @click="addFormat"
                        />
                        <div v-else-if="!isEdit" class="col-1"/>

                        <div class="col-sm"/>
                    </div>
                </td>
                <td colspan="2">
                    <div
                            class="row border-bottom m-0 p-0"
                            v-for="ws of currentDocument.documentFormatDto "
                            :key="ws.id"
                    >
                        <div class="col-sm my-2"> {{ ws.formatDto.name }}</div>
                        <div class="col-sm my-2"> {{ ws.qty }}</div>
                        <minus-icon
                                class="col-1  my-2"
                                fill-color="red"
                                v-if="isEdit"
                                @click="removeFormat(perm)"
                        />
                        <div v-else-if="!isEdit" class="col-1"/>

                    </div>

                </td>
            </tr>
            </tbody>
        </table>

        <div class="row m-auto w-75" v-if="isEdit && !isDelete">
            <b-button class="col-3 m-auto mt-xl-5 bg-light btn btn-outline-success text-success " @click="saveDocument">
                <h5 class="m-auto">
                    {{ $t('message.button.save') }}
                </h5>
            </b-button>
            <div class="col-3"/>
            <b-button v-if="isCreate" class="col-3 m-auto mt-xl-5" @click="goBack">
                <h5 class="m-auto">
                    {{ $t('message.button.back') }}
                </h5>
            </b-button>
            <b-button v-else-if="!isCreate" class="col-3 m-auto mt-xl-5" @click="cancel">
                <h5 class="m-auto">
                    {{ $t('message.button.cancel') }}
                </h5>
            </b-button>
        </div>
        <div class="row m-auto w-75" v-else-if="!isEdit && isDelete">
            <b-button class="col-3 m-auto mt-xl-5 bg-light btn btn-outline-danger text-danger " @click="deleteDocument">
                <h5 class="m-auto">
                    {{ $t('message.button.confirm_delete') }}
                </h5>
            </b-button>
            <div class="col-3"/>

            <b-button class="col-3 m-auto mt-xl-5" @click="cancel">
                <h5 class="m-auto">
                    {{ $t('message.button.cancel') }}
                </h5>
            </b-button>
        </div>
        <div class="row m-auto w-75" v-else>
            <b-button class="col-3 m-auto mt-xl-5" @click="goBack">
                <h5 class="m-auto">
                    {{ $t('message.button.back') }}
                </h5>
            </b-button>
            <b-button class="col-3 m-auto mt-xl-5 bg-light btn btn-outline-warning text-warning " @click="editDocument">
                <h5 class="m-auto">
                    {{ $t('message.button.edit') }}
                </h5>
            </b-button>
            <b-button class="col-3 m-auto mt-xl-5 bg-light btn btn-outline-danger text-danger "
                      @click="attemptDeleteDocument">
                <h5 class="m-auto">
                    {{ $t('message.button.delete') }}
                </h5>
            </b-button>
        </div>

    </div>
    <div v-else class="row">
        <h5>! DOCUMENT NOT FOUND</h5>
        <b-button class="col-3 m-auto mt-xl-5" @click="goBack">
            <h5 class="m-auto">
                {{ $t('message.button.back') }}
            </h5>
        </b-button>

    </div>

</template>

<script>
    import {mapActions, mapGetters} from "vuex";
    import router from "@/router";

    export default {
        name: "Document",
        data() {
            return {
                isEdit: false,
                isDelete: false,
                isCreate: false,
                id: 0,
                currentDocument: {...this.DOCUMENT}
            }
        },
        props: {
            document: {
                type: Object,
                default: () => {
                    return {}
                }
            }
        },
        computed: {
            ...mapGetters([
                'DOCUMENT'
            ]),
            getItemId() {
                return this.$route.params.id;
            },
            getFormatsLength() {
                if (this.DOCUMENT.documentFormatDto !== undefined && this.DOCUMENT.documentFormatDto.length > 0) {
                    return this.DOCUMENT.documentFormatDto.length;
                }
                return 0;
            },
            isSigned() {
                return this.currentDocument.documentApprovalsDto !== undefined &&
                    this.currentDocument.documentApprovalsDto.designerName !== undefined &&
                    this.currentDocument.documentApprovalsDto.designerName !== ''
            }

        },
        methods: {
            ...mapActions([
                'GET_DOCUMENT',
                'DELETE_DOCUMENT',
                'GET_ALL_DOCUMENTS',
                'CREATE_DOCUMENT',
                'CREATE_SIGNED_DOCUMENT',
                'UPDATE_DOCUMENT',
                'UPDATE_SIGNED_DOCUMENT',
            ]),
            goBack() {
                router.go(-1)
            },
            editDocument() {
                this.isEdit = true;
            },
            saveDocument() {
                if (this.isCreate) {
                    if (this.isSigned) {
                        this.CREATE_SIGNED_DOCUMENT(this.currentDocument).then(
                            () => {
                                router.push('/planner/documents')
                            }
                        );
                    } else {
                        this.CREATE_DOCUMENT(this.currentDocument).then(
                            () => {
                                router.push('/planner/documents')
                            }
                        );
                    }

                } else {
                    if (this.isSigned) {
                        this.UPDATE_SIGNED_DOCUMENT(this.currentDocument).then(
                            () => {
                                router.push('/planner/documents')
                            }
                        );
                    } else {
                        this.UPDATE_DOCUMENT(this.currentDocument).then(
                            () => {
                                router.push('/planner/documents')
                            }
                        );
                    }


                }
                this.isEdit = false;
                this.isCreate = false;
            },
            attemptDeleteDocument() {
                this.isDelete = true;
            },
            deleteDocument() {
                this.isDelete = false;
                this.DELETE_DOCUMENT(this.id).then(
                    () => {
                        router.push('/planner/documents')
                    }
                );
            },
            cancel() {
                this.isEdit = false;
                this.isDelete = false;
                this.currentDocument = this.DOCUMENT;
            },
            addFormat() {
            },
            removeFormat(value) {
                console.log(value);
            },
        },
        mounted() {
            this.id = this.$route.params.id;
            if (this.id == 0) {
                this.isCreate = true;
                this.isEdit = true;
                this.currentDocument = {};
                this.currentDocument.documentApprovalsDto = {};
                this.currentDocument.documentFormatDto = [];

            } else {
                this.GET_DOCUMENT(this.id).then(() =>
                    this.currentDocument = this.DOCUMENT
                );
            }
        }
    }
</script>

<style scoped>

</style>