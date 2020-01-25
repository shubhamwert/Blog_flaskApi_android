"""empty message

Revision ID: 8ed602fcb88d
Revises: 
Create Date: 2020-01-25 10:18:59.499744

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = '8ed602fcb88d'
down_revision = None
branch_labels = None
depends_on = None


def upgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    op.create_table('post',
    sa.Column('writer_name', sa.String(length=10), nullable=True),
    sa.Column('postIdconda', sa.Integer(), nullable=False),
    sa.Column('post_content', sa.String(length=100), nullable=True),
    sa.PrimaryKeyConstraint('postIdconda')
    )
    op.create_index(op.f('ix_post_post_content'), 'post', ['post_content'], unique=False)
    op.create_index(op.f('ix_post_writer_name'), 'post', ['writer_name'], unique=False)
    op.create_table('user',
    sa.Column('id', sa.Integer(), nullable=False),
    sa.Column('username', sa.String(length=10), nullable=True),
    sa.Column('name', sa.String(length=10), nullable=True),
    sa.PrimaryKeyConstraint('id')
    )
    op.create_index(op.f('ix_user_name'), 'user', ['name'], unique=False)
    op.create_index(op.f('ix_user_username'), 'user', ['username'], unique=True)
    # ### end Alembic commands ###


def downgrade():
    # ### commands auto generated by Alembic - please adjust! ###
    op.drop_index(op.f('ix_user_username'), table_name='user')
    op.drop_index(op.f('ix_user_name'), table_name='user')
    op.drop_table('user')
    op.drop_index(op.f('ix_post_writer_name'), table_name='post')
    op.drop_index(op.f('ix_post_post_content'), table_name='post')
    op.drop_table('post')
    # ### end Alembic commands ###