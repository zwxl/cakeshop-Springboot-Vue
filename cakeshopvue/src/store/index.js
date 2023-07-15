import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        collapse: false
    },
    mutations: {
        change (state) {
            console.log("gsdgfdfg")
            state.collapse = !state.collapse
        }
    }
})

export default store