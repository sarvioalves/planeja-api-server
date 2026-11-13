import { Component, OnInit, inject } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CartaoService } from '../cartao-service';
import { DadosCartaoForm, DetalhesCartao } from '../dados-cartao';
import { ValidationErrorResponse } from '../../common/validation/validation-error-model';
import { CommonModule } from '@angular/common';
import { ToastrService } from 'ngx-toastr';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
import { Header } from '../../common/components/header/header';

interface CadastroCartaoForm {
  nome: FormControl<String>;
  bandeira: FormControl<String>;
}

@Component({
  selector: 'app-cadastro-cartao',
  imports: [ReactiveFormsModule, CommonModule, RouterModule, Header],
  templateUrl: './cadastro-cartao.html',
  styleUrl: './cadastro-cartao.scss',
})
export class CadastroCartao implements OnInit {

  form!: FormGroup<CadastroCartaoForm>;
  rotaAtiva = inject(ActivatedRoute);
  service = inject(CartaoService);
  toastr = inject(ToastrService);
  idCartaoEdicao?: string | null;

  ngOnInit(): void {
    this.form = new FormGroup<CadastroCartaoForm>({
      nome: new FormControl('', { nonNullable: true, validators: Validators.required }),
      bandeira: new FormControl('', { nonNullable: true, validators: Validators.required })
    });

    this.carregarDadosParaEdicao();

  }

  carregarDadosParaEdicao() {
    this.idCartaoEdicao = this.rotaAtiva.snapshot.queryParamMap.get('id')

    if (!this.idCartaoEdicao) {
      return
    }
    this.service
      .obterPorId(this.idCartaoEdicao)
      .subscribe({
        next: (cartao) => {
          this.form.patchValue({
            nome: cartao.nome,
            bandeira: cartao.bandeira
          })
        },
        error: () => this.toastr.error('Erro ao carregar dados do cartão')
      })
  }

  isFormInvalid(): boolean {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      this.toastr.error('Erro de validação. Verifique os valores informados. ')
      return true;
    }
    return false;
  }

  handleSubmit() {
    if (this.isFormInvalid()) {
      return;
    }

    const dadosCartao = this.form.value as DadosCartaoForm;

    const requisacao: Observable<DetalhesCartao | void> = this.idCartaoEdicao ?
      this.service.atualizar(this.idCartaoEdicao, dadosCartao) :
      this.service.criar(dadosCartao);


    requisacao
      .subscribe({
        next: (response) => {
          this.toastr.success('Cartão cadastrado/atualizado com sucesso!')
          this.form.reset();
          this.idCartaoEdicao = null;
        },
        error: (error) => this.onApiError(error)
      });
  }

  private aplicarErrosValidacao(error: ValidationErrorResponse) {
    error.camposInvalidos.forEach(ci => {
      const control = this.form.get(ci.campo);
      if (control) {
        control.setErrors({ apiError: ci.erro })
        control.markAsTouched();
      }
    })
  }

  private onApiError(res: any): void {
    if (res.status === 422) {
      this.aplicarErrosValidacao(res.error);
      this.toastr.error('Erro de validação. Verifique os valores informados. ')
      return;
    }
    this.toastr.error('Ocorreu um erro ao processar a requisição');
    console.error(Response.error)
  }

}
