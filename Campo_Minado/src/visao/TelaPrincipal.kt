package visao

import modelo.Tabuleiro
import modelo.TabuleiroEvento
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

var lblQtdMina = JLabel()
var countMina = 20
var perdeu = 0
var ganhou = 0
var qtdLinha = 16
var qtdColuna = 30
var qtdMinas = 1
var titulo = "Escolha um Nível"
var largura = 690
var altura = 70
var jogo = false
var lblPerdeu = JLabel("Perdeu: " + perdeu)
var lblGanhou = JLabel("Ganhou: " + ganhou)

class TelaPrincipal: JFrame(){
    private var tabuleiro = Tabuleiro(qtdLinhas = qtdLinha, qtdColunas = qtdColuna, qtdMinas = qtdMinas)
    private var painelTabuleiro = PainelTabuleiro(tabuleiro)
    var botao1 = JButton("Iniciar")
    var panelLabel = JPanel()
    var lblRelogio = JLabel()
    var tempo = TimerToLabel(1000, lblRelogio, 5, tabuleiro, panelLabel, botao1, painelTabuleiro, lblPerdeu)

//    class SimpleThread: Thread() {
//        public override fun run() {
//            for (i in 10 downTo 1){
//                Thread.sleep(1000)
//
//            }
//        }
//    }


    class TimerToLabel(private val delay: Int, private val label: JLabel, private val tempo1: Int, tabuleiro: Tabuleiro,
                       private val panel: JPanel, private val botao: JButton, private val painelTabuleiro: PainelTabuleiro,
                       private val jPerdeu: JLabel) : ActionListener {

        private var timer: Timer? = null
        var i = tempo1
        var tab = tabuleiro
        var painel1 = panel
        var botao1 = botao

        fun init() {
            timer = Timer(delay, this)
            timer!!.start()
        }

        fun stop() {
            timer!!.stop()
        }


        override fun actionPerformed(e: ActionEvent) {
            // faça aqui sua consulta
            label.text = "Tempo: " + i--
            label.updateUI()
            if(i < -1){
                label.text = ""
                JOptionPane.showMessageDialog(null,"Você Perdeu!")
                tab.reiniciar()
                timer!!.stop()
                painel1.add(botao1)
                painelTabuleiro.repaint()
                jPerdeu.text = "Perdeu: ${++perdeu}"
            }
        }

//    companion object {
//
//        @JvmStatic
//        fun main(args: Array<String>) {
//            val timer = TimerToLabel(1000, lblNewLabel_1)
//            timer.init()
//        }
//    }

    }


//    fun contar(a: Int) : Int {
//        var i = a
//        Thread.sleep(1000)
//        return i--
//    }

    init {

       if (!jogo) {

           tabuleiro = Tabuleiro(qtdLinha, qtdColuna, qtdMinas)
           painelTabuleiro = PainelTabuleiro(tabuleiro)

           tabuleiro.onEvento(this:: mostrarResultado)
           //add(painelTabuleiro)
           setSize(largura,altura) //ajustar tamanho se necessário
           setLocationRelativeTo(null)
           defaultCloseOperation = EXIT_ON_CLOSE

           title = titulo
           var panelPrincipal = JPanel()
           panelPrincipal.layout = BorderLayout(0,0)
           //panelPrincipal.add(painelTabuleiro, BorderLayout.CENTER)
           add(panelPrincipal)

           panelPrincipal.add(panelLabel, BorderLayout.NORTH)
           panelLabel.setLayout(FlowLayout(FlowLayout.CENTER,5,5))

           val mola1 = Box.createHorizontalGlue()
           panelLabel.add(mola1)

           var botao = JButton("Nível 1")
           botao.addActionListener{
               qtdLinha = 2 + (Math.random() * 10).toInt();
               qtdColuna =  5 + (Math.random() * 15).toInt();
               qtdMinas = 1 + (Math.random() * 4).toInt();
               lblQtdMina.text = "✸: " + qtdMinas
               tabuleiro = Tabuleiro(qtdLinhas = qtdLinha, qtdColunas = qtdColuna, qtdMinas = qtdMinas)
               painelTabuleiro = PainelTabuleiro(tabuleiro)
               jogo = true
               altura = 438
               titulo = "Campo Ninado"
               this.isVisible = false
               this.dispose()
               TelaPrincipal()
           }
           panelLabel.add(botao)

           val mola2 = Box.createHorizontalGlue()
           panelLabel.add(mola2)

           var botao1 = JButton("Nível 2")
           botao1.addActionListener{
               qtdLinha = 12 + (Math.random() * 6).toInt();
               qtdColuna =  20 + (Math.random() * 5).toInt();
               qtdMinas = 5 + (Math.random() * 5).toInt();
               lblQtdMina.text = "✸: " + qtdMinas
               tabuleiro = Tabuleiro(qtdLinhas = qtdLinha, qtdColunas = qtdColuna, qtdMinas = qtdMinas)
               painelTabuleiro = PainelTabuleiro(tabuleiro)
               jogo = true
               altura = 438
               titulo = "Campo Ninado"
               this.isVisible = false
               this.dispose()
               TelaPrincipal()
           }
           panelLabel.add(botao1)

           val mola3 = Box.createHorizontalGlue()
           panelLabel.add(mola3)

           var botao2 = JButton("Nível 3")
           botao2.addActionListener{
               qtdLinha = 16 + (Math.random() * 4).toInt();
               qtdColuna =  25 + (Math.random() * 5).toInt();
               qtdMinas = 15 + (Math.random() * 20).toInt();
               lblQtdMina.text = "✸: " + qtdMinas
               tabuleiro = Tabuleiro(qtdLinhas = qtdLinha, qtdColunas = qtdColuna, qtdMinas = qtdMinas)
               painelTabuleiro = PainelTabuleiro(tabuleiro)
               jogo = true
               altura = 438
               titulo = "Campo Ninado"
               this.isVisible = false
               this.dispose()
               TelaPrincipal()
           }
           panelLabel.add(botao2)

           isVisible = true

        } else {
           tabuleiro.onEvento(this:: mostrarResultado)
           //add(painelTabuleiro)
           setSize(largura,altura) //ajustar tamanho se necessário
           setLocationRelativeTo(null)
           defaultCloseOperation = EXIT_ON_CLOSE

           title = titulo
           var panelPrincipal = JPanel()
           panelPrincipal.layout = BorderLayout(0,0)
           panelPrincipal.add(painelTabuleiro, BorderLayout.CENTER)
           add(panelPrincipal)

           panelPrincipal.add(panelLabel, BorderLayout.NORTH)
           panelLabel.setLayout(FlowLayout(FlowLayout.CENTER,5,5))

           val mola1 = Box.createHorizontalGlue()
           panelLabel.add(mola1)

           panelLabel.add(lblGanhou)

           val mola2 = Box.createHorizontalGlue()
           panelLabel.add(mola2)

           panelLabel.add(lblPerdeu)

           val mola3 = Box.createHorizontalGlue()
           panelLabel.add(mola3)

           panelLabel.add(lblQtdMina)

           val mola4 = Box.createHorizontalGlue()
           panelLabel.add(mola4)

           panelLabel.add(lblRelogio)

           val mola5 = Box.createHorizontalGlue()
           panelLabel.add(mola5)

           var botao1 = JButton("Tempo")
            botao1.addActionListener{
                tempo = TimerToLabel(1000, lblRelogio, 5, tabuleiro, panelLabel, botao1, painelTabuleiro, lblPerdeu)
                tempo.init()
            }
            panelLabel.add(botao1)

            val mola7 = Box.createHorizontalGlue()
            panelLabel.add(mola7)

           var botao2 = JButton("Escolher outro Nível")
           botao2.addActionListener{
               jogo = false
               altura = 70
               titulo = "Escolha Nível"
               this.isVisible = false
               this.dispose()
               TelaPrincipal()
           }
           panelLabel.add(botao2)

           val mola6 = Box.createHorizontalGlue()
           panelLabel.add(mola6)

            isVisible = true
        }
    }


    private fun mostrarResultado(evento: TabuleiroEvento){
        SwingUtilities.invokeLater {
            val msg = when(evento){
                TabuleiroEvento.VITORIA-> "Você ganhou!"
                TabuleiroEvento.DERROTA-> "Você perdeu... :("
            }

            if (tabuleiro.objetivoAlcancado()){
                ganhou++
                if (lblRelogio.text != "") {
                    tempo.stop()
                }
                lblRelogio.text = ""
                jogo = false
            }else{
                perdeu++
                if (lblRelogio.text != "") {
                    tempo.stop()
                }
                lblRelogio.text = ""
                jogo = false
            }
            JOptionPane.showMessageDialog(this, msg)
            atualiza_label()

//            titulo = "Escolha um Nível"
//            altura = 70
//
//            this.isVisible = false
//            this.dispose()
//            TelaPrincipal()

            qtdLinha = 2 + (Math.random() * 10).toInt();
            qtdColuna =  5 + (Math.random() * 15).toInt();
            qtdMinas = 1 + (Math.random() * 4).toInt();
            lblQtdMina.text = "✸: " + qtdMinas
            tabuleiro = Tabuleiro(qtdLinhas = qtdLinha, qtdColunas = qtdColuna, qtdMinas = qtdMinas)
            painelTabuleiro = PainelTabuleiro(tabuleiro)
            jogo = true
            altura = 438
            titulo = "Campo Ninado"
            this.isVisible = false
            this.dispose()
            TelaPrincipal()

        }
    }

    private fun atualiza_label(){
        lblPerdeu.text = "Perdeu: ${perdeu}"
        lblGanhou.text = "Ganhou: ${ganhou}"
    }

}

public fun clicarMina(){
    lblQtdMina.text = "✸ : ${qtdMinas}"
}

fun main(args: Array<String>) {
    TelaPrincipal()
}