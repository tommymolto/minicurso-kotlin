package br.edu.infnet.aulakotlin

var boletim = Boletim()
fun main() {



    var controle = "ENTRE"
    while(controle != "SAIR"){
        when(controle){
            "MEDIA" -> exibirMediaDaTurma()
            "NOTABAIXA" -> exibirNotaBaixa()
            "NOTAALTA" -> exibirNotaAlta()
            else -> insereAluno()
        }
        println("O que deseja?")
        println("Tecle em Enter para cadastrar mais um aluno")
        println("Digite MEDIA para a media da turma")
        println("Digite NOTABAIXA para ver a menor nota da turma")
        println("Digite NOTAALTA para ver a maior nota da turma")
        println("Digite SAIR para encerrar o programa")
        controle = readLine()!!

    }
}

fun exibirMediaDaTurma(){
    val mediaTurma = boletim.alunos.map { it -> it.calcularMedia() }.average()
    println("A média da turma foi  de $mediaTurma")

}
fun exibirNotaBaixa(){

    val notaMinima = boletim.alunos.minWith(Comparator.comparingInt {it.provaUm })
    println("A menor nota foi ${ notaMinima.provaUm} de ${ notaMinima.nome }")
}
fun exibirNotaAlta(){
    val notaMinima = boletim.alunos.maxWith(Comparator.comparingInt {it.provaUm })
    println("A maior nota foi ${ notaMinima.provaUm} de ${ notaMinima.nome }")
}


fun insereAluno(){
    println("Insira o nome de um aluno")
    val nome = readLine()!!
    println("Insira a nota da primeira prova")
    val provaum = readLine()!!
    println("Insira a nota da segunda prova")
    val provadois = readLine()!!
    val aluno = Aluno(nome, provaum.toInt(), provadois.toInt())
    boletim.adicionaAluno(aluno)
    aluno.bemVindo()
}
data class Aluno(var nome: String, var provaUm: Int, var provaDois: Int){
    fun bemVindo(){
        println("Bem vindo, ${nome}")
        println("Sua media é  ${calcularMedia()}")
    }
    fun calcularMedia(): Double {
        return ((provaUm + provaDois).toDouble() / 2)
    }

}
class Boletim(){

    var alunos = mutableListOf<Aluno>()
    fun adicionaAluno(_aluno: Aluno){
        alunos.add(_aluno)
        println("total de alunos = ${ alunos.size} ")
    }

}