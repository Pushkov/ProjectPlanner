<template>
    <div class="nic-popup-body container-fluid">
        <div role="group" class="row m-0 mt-1 mx-2">
            <div class="col-4 mr-2 ">
                {{$t('engineer.login')}}:
            </div>
            <b-form-input
                    id="input-login"
                    class="col-sm text-secondary border rounded-lg m-0"
                    :readonly="!isEdit"
                    :value="itemUser.login"
                    v-model="itemUser.login"
                    :state="getErrorInputLogin"
                    :class="getErrorLogin"
                    aria-describedby="input-login-help input-login-feedback"
                    trim
            />
        </div>
        <b-form-text class="m-0">
            <small v-if="getErrorInputLogin" class="text-sm text-secondary m-auto" style="font-size: xx-small">Логин
                корректен&nbsp;</small>
            <small v-else class="text-sm text-danger m-auto" style="font-size: xx-small">Логин не может быть меньше 3
                символов&nbsp;</small>
        </b-form-text>

        <div class="row m-0 mx-2" v-if="itemUser.id === undefined">
            <div class="col-4 mr-2 ">
                {{$t('engineer.password')}}:
            </div>
            <b-input
                    class="col-sm text-secondary  border rounded-lg"
                    :class="getErrorPassword"
                    :readonly="!isEdit"
                    :value="itemUser.password"
                    v-model="itemUser.password"/>
        </div>
        <ErrorMessage
                :item="error.password"
        />

        <div class="row m-0 mx-2">
            <div class="col-4 mr-2 ">
                {{$t('engineer.first_name')}}:
            </div>
            <b-input
                    class="col-sm text-secondary  border rounded-lg"
                    :class="getErrorFirstName"
                    :readonly="!isEdit"
                    :value="itemUser.firstName"
                    v-model="itemUser.firstName"/>
        </div>
        <ErrorMessage
                :item="error.firstName"
        />
        <div class="row my-1 mx-2">
            <div class="col-4 mr-2">
                {{$t('engineer.last_name')}}:
            </div>
            <b-input
                    :class="getErrorLastName"
                    class="col-sm text-secondary border rounded-lg"
                    :readonly="!isEdit"
                    :value="itemUser.lastName"
                    v-model="itemUser.lastName"/>
        </div>
        <ErrorMessage class="m-0"
                      :item="error.lastName"
        />

        <div class="row my-1 mx-2">
            <div class="col-4 mr-2">
                {{$t('engineer.patr_name')}}:
            </div>
            <b-input
                    class="col-sm text-secondary border rounded-lg"
                    :readonly="!isEdit"
                    :value="itemUser.patronymicName"
                    v-model="itemUser.patronymicName"/>
        </div>
        <ErrorMessage class="m-0"
                      :item="error.patronymicName"
        />

        <div class="row my-1 mx-2">
            <div class="col-4 mr-2">
                {{$t('engineer.position')}}:
            </div>
            <b-input
                    v-if="!isEdit"
                    class="col-sm text-secondary border rounded-lg"
                    readonly
                    :class="getErrorRole"
                    :value="itemUser.roleId"
                    v-model="itemUser.roleName"/>
            <b-form-select
                    :class="getErrorRole"
                    v-else
                    v-model="itemUser.roleId"
                    :options="roles"
                    class="col-sm text-secondary border rounded-lg"
                    value-field="id"
                    text-field="name"
                    disabled-field="notEnabled"
            ></b-form-select>
        </div>
        <ErrorMessage class="m-0"
                      :item="error.roleId"
        />

        <div class="row my-1 mx-2">
            <div class="col-4 mr-2">
                {{$t('engineer.department')}}:
            </div>
            <b-input
                    :class="getErrorDepartment"
                    v-if="!isEdit"
                    class="col-sm text-secondary border rounded-lg"
                    readonly
                    :value="itemUser.departmentId"
                    v-model="itemUser.departmentName"/>
            <b-form-select
                    :class="getErrorDepartment"
                    v-else
                    v-model="itemUser.departmentId"
                    :options="departments"
                    class="col-sm text-secondary border rounded-lg"
                    value-field="id"
                    text-field="fullName"
                    disabled-field="notEnabled"
            ></b-form-select>
        </div>
        <ErrorMessage class="m-0"
                      :item="error.departmentId"
        />

        <div class="row my-1 mx-2">
            <div class="col-4 mr-2">
                {{$t('engineer.phone')}}:
            </div>
            <b-input
                    :class="getErrorPhone"
                    class="col-sm text-secondary border rounded-lg"
                    placeholder="xx-xx"
                    style=""

                    :readonly="!isEdit"
                    :value="itemUser.contactDetails.phone"
                    v-model="itemUser.contactDetails.phone"/>
        </div>
        <ErrorMessage class="m-0"
                      :item="error.contactDetails_phone"
        />

        <div class="row my-1 mx-2">
            <div class="col-4 mr-2">
                {{$t('engineer.mobile')}}:
            </div>
            <b-input
                    :class="getErrorMobile"
                    class="col-sm text-secondary border rounded-lg"
                    :readonly="!isEdit"
                    :value="itemUser.contactDetails.mobile"
                    v-model="itemUser.contactDetails.mobile"/>
        </div>
        <ErrorMessage class="m-0"
                      :item="error.contactDetails_mobile"
        />

        <div class="row my-1 mx-2">
            <div class="col-4 mr-2">
                {{$t('engineer.email')}}:
            </div>
            <b-input
                    :class="getErrorEmail"
                    class="col-sm text-secondary border rounded-lg"
                    :readonly="!isEdit"
                    :value="itemUser.contactDetails.email"
                    v-model="itemUser.contactDetails.email"/>
        </div>
        <ErrorMessage class="m-0"
                      :item="error.contactDetails_email"
        />

        <div class="row my-1 mx-2">
            <div class="col-4 mr-2">
                {{$t('engineer.office')}}:
            </div>
            <b-input
                    :class="getErrorOffice"
                    class="col-sm text-secondary border rounded-lg"
                    :readonly="!isEdit"
                    :value="itemUser.contactDetails.office"
                    v-model="itemUser.contactDetails.office"/>
        </div>
        <ErrorMessage class="m-0"
                      :item="error.contactDetails_office"
        />

        <div class="row my-1 mx-2">
            <div class="col-4 mr-2">
                {{$t('engineer.status')}}:
            </div>
            <b-input
                    v-if="!isEdit"
                    class="col-sm text-secondary border rounded-lg"
                    readonly
                    :value="itemUser.status"
                    v-model="itemUser.status"/>
            <b-form-select
                    v-else
                    v-model="itemUser.status"
                    :options="statuses"
                    class="col-sm text-secondary border rounded-lg"
                    value-field="status"
                    text-field="status"
                    disabled-field="notEnabled"
            ></b-form-select>
        </div>

    </div>
</template>

<script>
    import ErrorMessage from "@/components/ErrorMessage";

    export default {
        name: "EngineerViewModal",
        components: {ErrorMessage},
        data() {
            return {
                itemUser: {...this.item},
            }
        },
        props: {
            item: {
                type: Object,
                default: () => {
                    return {}
                }
            },
            roles: [],
            departments: [],
            statuses: [],
            isEdit: {
                type: Boolean,
                default: false
            },
            error: {
                type: Object,
                default: () => {
                    return {}
                }
            }
        },
        computed: {
            getSubmitEnabled() {
                return !this.getErrorInputLogin();
            },

            getErrorInputLogin() {
                if (this.itemUser.login !== undefined) {
                    if (this.itemUser.login.length < 3) {
                        return false;
                    }
                    return true;
                }
                return false;
            },
            getErrorLogin() {
                return this.error.login !== undefined ? 'border border-danger' : '';
            },
            getErrorPassword() {
                return this.error.password !== undefined ? 'border border-danger' : '';
            },
            getErrorFirstName() {
                return this.error.firstName !== undefined ? 'border border-danger' : '';
            },
            getErrorLastName() {
                return this.error.lastName !== undefined ? 'border border-danger' : '';
            },
            getErrorRole() {
                return (this.error.roleId !== undefined) ? 'border border-danger' : '';
            },
            getErrorDepartment() {
                return this.error.departmentId !== undefined ? 'border border-danger' : '';
            },
            getErrorPhone() {
                return this.error.contactDetails_phone !== undefined ? 'border border-danger' : '';
            },
            getErrorMobile() {
                return this.error.contactDetails_mobile !== undefined ? 'border border-danger' : '';
            },
            getErrorEmail() {
                return this.error.contactDetails_email !== undefined ? 'border border-danger' : '';
            },
            getErrorOffice() {
                return this.error.contactDetails_office !== undefined ? 'border border-danger' : '';
            },
        },
        methods: {
            returnUser() {
                this.$emit('returnUser', this.itemUser);
            },
            isSubmitEnabled() {
                this.$emit('isSubmitEnabled', this.getSubmitEnabled);
            }
        }

    }
</script>

<style scoped>
    ::placeholder {
        color: #b1b1b1;
    }

</style>