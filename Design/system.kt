class system {
    private val personIdToMachineIdMap: map<Int, Int>? = null
    private val machineIdToMachineMap: map<Int, Machine>? = null

    internal fun getMachine(machineId: Int): Machine

    internal fun getPerson(personId: Int): Person {
        val machienId = personIdToMachienIdMap[personId]
        val m = machineIdToMachineMap!![machienId]
        return m.getPersonWithId(personId)
    }
}
