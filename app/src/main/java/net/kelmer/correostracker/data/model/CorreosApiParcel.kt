package net.kelmer.correostracker.data.model


data class CorreosApiParcel(val codEnvio: String,
                            val eventos: List<CorreosApiEvent>){


    fun isDelivered(): Boolean = eventos.get(eventos.size-1).desTextoResumen.equals("Entregado")
}