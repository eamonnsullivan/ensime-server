// Copyright: 2010 - 2017 https://github.com/ensime/ensime-server/graphs
// License: http://www.gnu.org/licenses/gpl-3.0.en.html
package org.ensime.core

import java.io.File

import org.ensime.api._
import org.ensime.util.ensimefile._
import org.ensime.util.file._
import shapeless._

/**
 * Goes through sealed families and gets the canonical path of `File`
 * and `Path` instances.
 *
 * Not to be confused with "the other" Cannon ;-)
 */
object Canon extends Poly1 {
  // people extend File, so we have to handle subtypes
  implicit def caseFile[F <: File]: Case[F] { type Result = File } =
    at[F](f => f.canon)

  implicit def caseEnsimeFile[EF <: EnsimeFile]
    : Case[EF] { type Result = EnsimeFile } = at[EF] { f =>
    f.canon
  }
}

object Canonised {
  def apply[T](t: T)(implicit everywhere: Everywhere[Canon.type, T]) =
    everywhere(t)
}
