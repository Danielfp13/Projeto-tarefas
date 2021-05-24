import { Convidado } from './Convidado'

export class Tarefa {
   id: number
   nome: string
   dataHora: string
   duracao: string
   local: string
   convidados: Convidado[]
   idUsuario: number
 }