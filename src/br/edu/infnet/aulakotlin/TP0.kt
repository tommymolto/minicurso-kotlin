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
    val mediaTurma = boletim.exibirMediaDaTurma()
    println("A média da turma foi  de $mediaTurma")

}
fun exibirNotaBaixa(){

    val notaMinima = boletim.exibirNotaBaixa()
    println("A menor nota foi ${ notaMinima.menorNota()} de ${ notaMinima.nome }")
}
fun exibirNotaAlta(){
    val notaMaxima = boletim.exibirNotaAlta()
    println("A maior nota foi ${ notaMaxima.maiorNota()} de ${ notaMaxima.nome }")
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
    fun menorNota(): Int {
        return minOf(provaUm, provaDois)
    }
    fun maiorNota(): Int {
        return maxOf(provaUm, provaDois)
    }

}
class Boletim(){

    var alunos = mutableListOf<Aluno>()
    fun adicionaAluno(_aluno: Aluno){
        alunos.add(_aluno)
        println("total de alunos = ${ alunos.size} ")
    }
    fun exibirMediaDaTurma(): Double {
        return boletim.alunos.map { it -> it.calcularMedia() }.average()
    }
    fun exibirNotaAlta(): Aluno {
        return alunos.maxWith(Comparator.comparingInt {it.maiorNota() })
    }
    fun exibirNotaBaixa(): Aluno {
        return alunos.minWith(Comparator.comparingInt {it.menorNota() })
    }

}